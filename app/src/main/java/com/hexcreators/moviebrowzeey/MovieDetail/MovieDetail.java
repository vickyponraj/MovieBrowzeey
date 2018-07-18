package com.hexcreators.moviebrowzeey.MovieDetail;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.hexcreators.moviebrowzeey.Data.Local.MovieLocalDataSource;
import com.hexcreators.moviebrowzeey.Data.Model.Genres;
import com.hexcreators.moviebrowzeey.Data.Model.Movie;
import com.hexcreators.moviebrowzeey.Data.Model.Results;
import com.hexcreators.moviebrowzeey.Data.Remote.MovieRemoteDataSource;
import com.hexcreators.moviebrowzeey.MovieBrowzeey;
import com.hexcreators.moviebrowzeey.MovieList.MovieListAdapter;
import com.hexcreators.moviebrowzeey.R;
import com.hexcreators.moviebrowzeey.Z_AppUtills.BaseActivity;

import java.util.List;
import java.util.Objects;

import static com.hexcreators.moviebrowzeey.Z_AppUtills.AppUtills.ClickedData_TAG;
import static com.hexcreators.moviebrowzeey.Z_AppUtills.AppUtills.prepareDataSource;
import static com.hexcreators.moviebrowzeey.Z_AppUtills.AppUtills.showSnackbar;

public class MovieDetail extends BaseActivity implements MovieDetailContract.View {
    private Snackbar snackbar;
    private static final String TAG = MovieDetail.class.getSimpleName();
    private Toolbar toolbar;
    private AppBarLayout app_bar;
    private ImageView iv_expandedImage;
    private ImageView iv_movie_poster;
    private MovieDetailPresenter presenter;
    private ShimmerFrameLayout shimmer_appbar;
    private ShimmerFrameLayout shimmer_suggestion;
    private RelativeLayout rl_dataView;
    private TextView tv_movie_name;
    private TextView tv_rating;
    private TextView tv_genres;
    private TextView tv_movie_overview;
    private SwipeRefreshLayout srl_detail;
    private MovieListAdapter adapter;
    private RecyclerView rview_suggestions;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private TextView tv_duration;
    private LinearLayout ll_movie_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_02);


        initViews();

        initControllers();

        initPresenters();

        getExtraDatas(getIntent());

    }

    private void getExtraDatas(Intent intent) {
        if (intent.getExtras() != null) {
            String string = intent.getExtras().getString(ClickedData_TAG, null);
            if (string != null) {
                presenter.loadMovieDetails(Integer.parseInt(string));
            }
        }
    }

    private void initPresenters() {

        presenter = new MovieDetailPresenter(this, prepareDataSource(MovieRemoteDataSource.getInstance(), MovieLocalDataSource.getINSTANCE()));

    }

    private void initViews() {
        iv_expandedImage = findViewById(R.id.iv_expandedImage);
        iv_movie_poster = findViewById(R.id.iv_movie_poster);
        shimmer_appbar = findViewById(R.id.shimmer_appbar);
        shimmer_suggestion = findViewById(R.id.shimmer_suggestion);
        rl_dataView = findViewById(R.id.rl_dataView);
        tv_movie_name = findViewById(R.id.tv_movie_name);
        tv_movie_overview = findViewById(R.id.tv_movie_overview);
        ll_movie_data = findViewById(R.id.ll_movie_data);
        rview_suggestions = findViewById(R.id.rview_suggestions);
        srl_detail = findViewById(R.id.srl_detail);
        tv_rating = findViewById(R.id.tv_rating);
        tv_genres = findViewById(R.id.tv_genres);
        tv_duration = findViewById(R.id.tv_duration);
    }

    private void initControllers() {
        setToolBarItem();

        adapter = new MovieListAdapter(callbacks);

        adapter.setMode(1);

        rview_suggestions.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        rview_suggestions.setAdapter(adapter);

        refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (presenter != null && presenter.getMovieID() != 0) {
                    presenter.loadMovieDetails(presenter.getMovieID());
                }
            }
        };

        srl_detail.setOnRefreshListener(refreshListener);
    }


    private void setToolBarItem() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Go Back");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void showProcessing() {
        srl_detail.setRefreshing(true);
        shimmer_appbar.setVisibility(View.VISIBLE);
        shimmer_appbar.startShimmer();
        rl_dataView.setVisibility(View.GONE);
    }

    @Override
    public void hideProcessing() {
        srl_detail.setRefreshing(false);
        shimmer_appbar.stopShimmer();
        shimmer_appbar.setVisibility(View.GONE);
        rl_dataView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMovie(Movie movie) {

        presenter.loadSimilar(movie.getId());

        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500/" + movie.getBackdrop_path())
                //.apply(new RequestOptions().centerCrop())
                .transition(DrawableTransitionOptions.withCrossFade(1000))
                .into(iv_expandedImage);
        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500/" + movie.getPoster_path())
                .apply(new RequestOptions().centerCrop())
                .transition(DrawableTransitionOptions.withCrossFade(1000))
                .into(iv_movie_poster);
        tv_movie_name.setText(movie.getTitle());
        tv_movie_overview.setText(movie.getOverview());
        tv_rating.setText(String.format("%2.2f / 10 ", movie.getVote_average()));


        List<Genres> genres = movie.getGenres();
        StringBuilder gen_builder = new StringBuilder();
        for (Genres genre : genres) {
            gen_builder.append(genre.getName());
            gen_builder.append(" / ");
        }

        int[] duration = new int[2];

        duration[0] = movie.getRuntime() / 60;
        duration[1] = movie.getRuntime() % 60;

        gen_builder.deleteCharAt(gen_builder.length() - 2);
        tv_duration.setText(String.format("%d hrs %02d mins", duration[0], duration[1]));
        tv_genres.setText(gen_builder.toString());
    }

    @Override
    public void showSimilarProcessing() {
        shimmer_suggestion.setVisibility(View.VISIBLE);
        shimmer_suggestion.startShimmer();
        rview_suggestions.setVisibility(View.GONE);
    }

    @Override
    public void setSimilarMovies(List<Results> movies) {
        adapter.setMovies(movies);
        if (movies.size() >= 1) {
            rview_suggestions.smoothScrollToPosition(0);
        }
    }

    @Override
    public void hideSimilarProcessing() {
        shimmer_suggestion.stopShimmer();
        shimmer_suggestion.setVisibility(View.GONE);
        rview_suggestions.setVisibility(View.VISIBLE);
    }

    private MovieListAdapter.Callbacks callbacks = new MovieListAdapter.Callbacks() {
        @Override
        public void onItemClick(Results results) {
            presenter.loadMovieDetails(results.getId());
        }
    };

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            snackbar = Snackbar.make(findViewById(android.R.id.content), "No Internet Connection", Snackbar.LENGTH_INDEFINITE);
            snackbar.show();
        } else {
            onInternetConnected();
            snackbar = Snackbar.make(findViewById(android.R.id.content), "Internet Connected", Snackbar.LENGTH_SHORT);
            snackbar.setText("Internet Connected");
            snackbar.show();
        }
    }

    private void onInternetConnected() {
        if (rview_suggestions.getAdapter() != null) {
            if (presenter.getMovieID() != 0 && rview_suggestions.getAdapter().getItemCount() == 0)
                presenter.loadSimilar(presenter.getMovieID());
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        MovieBrowzeey.getInstance().setConnectivityListener(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MovieBrowzeey.getInstance().setConnectivityListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

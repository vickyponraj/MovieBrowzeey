package com.hexcreators.moviebrowzeey.MovieDetail;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.hexcreators.moviebrowzeey.Data.Local.MovieLocalDataSource;
import com.hexcreators.moviebrowzeey.Data.Model.Movie;
import com.hexcreators.moviebrowzeey.Data.Remote.MovieRemoteDataSource;
import com.hexcreators.moviebrowzeey.R;

import static com.hexcreators.moviebrowzeey.Z_AppUtills.AppUtills.prepareDataSource;

public class MovieDetail extends AppCompatActivity implements MovieDetailContract.View {

    private static final String TAG = MovieDetail.class.getSimpleName();
    private Toolbar toolbar;
    private AppBarLayout app_bar;
    private ImageView iv_expandedImage;
    private ImageView iv_movie_poster;
    private MovieDetailPresenter presenter;
    private ShimmerFrameLayout  shimmer_appbar;
    private RelativeLayout rl_dataView;
    private TextView tv_movie_name;
    private TextView tv_movie_overview;

    private LinearLayout ll_movie_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        initViews();

        initControllers();

        initPresenters();
    }

    private void initPresenters() {

        presenter = new MovieDetailPresenter(this, prepareDataSource(MovieRemoteDataSource.getInstance(), MovieLocalDataSource.getINSTANCE()));


        presenter.loadMovieDetails(351286);
    }

    private void initViews() {
        toolbar                 =   findViewById(R.id.toolbar);
        app_bar                 =   findViewById(R.id.app_bar);
        iv_expandedImage        =   findViewById(R.id.iv_expandedImage);
        iv_movie_poster         =   findViewById(R.id.iv_movie_poster);
        shimmer_appbar          =   findViewById(R.id.shimmer_appbar);
        rl_dataView             =   findViewById(R.id.rl_dataView);
        tv_movie_name           =   findViewById(R.id.tv_movie_name);
        tv_movie_overview       =   findViewById(R.id.tv_movie_overview);
        ll_movie_data           =   findViewById(R.id.ll_movie_data);
    }

    private void initControllers() {
        setSupportActionBar(toolbar);
        setToolBarItem();
    }


    private void setToolBarItem() {
        getSupportActionBar().setTitle("");
    }

    @Override
    public void showProcessing() {
        shimmer_appbar.startShimmer();
        rl_dataView.setVisibility(View.GONE);
    }

    @Override
    public void hideProcessing() {
        shimmer_appbar.stopShimmer();
        shimmer_appbar.setVisibility(View.GONE);
        rl_dataView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMovie(Movie movie) {
        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500/"+movie.getBackdrop_path())
                //.apply(new RequestOptions().centerCrop())
                .into(iv_expandedImage);
        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500/"+movie.getPoster_path())
                .apply(new RequestOptions().centerCrop())
                .into(iv_movie_poster);
        tv_movie_name.setText(movie.getTitle());
        tv_movie_overview.setText(movie.getOverview());
    }
}

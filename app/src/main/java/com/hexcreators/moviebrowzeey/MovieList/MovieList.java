package com.hexcreators.moviebrowzeey.MovieList;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hexcreators.moviebrowzeey.Data.Local.MovieLocalDataSource;
import com.hexcreators.moviebrowzeey.Data.Model.Movie;
import com.hexcreators.moviebrowzeey.Data.Model.Results;
import com.hexcreators.moviebrowzeey.Data.Remote.MovieRemoteDataSource;
import com.hexcreators.moviebrowzeey.MovieBrowzeey;
import com.hexcreators.moviebrowzeey.MovieDetail.MovieDetail;
import com.hexcreators.moviebrowzeey.R;
import com.hexcreators.moviebrowzeey.Z_AppUtills.BaseActivity;
import com.hexcreators.moviebrowzeey.Z_AppUtills.ItemDecorationGrid;

import java.util.List;

import static com.hexcreators.moviebrowzeey.Z_AppUtills.AppUtills.ClickedData_TAG;
import static com.hexcreators.moviebrowzeey.Z_AppUtills.AppUtills.prepareDataSource;
import static com.hexcreators.moviebrowzeey.Z_AppUtills.AppUtills.showSnackbar;

public class MovieList extends BaseActivity implements MovieListContract.View {

    private MovieListPresenter presenter;
    private RecyclerView rv_list;
    private RecyclerView rv_list_local;
    private MovieListAdapter adapter;
    private RelativeLayout rl_root;
    private MovieLocalListAdapter localListAdapter;


    private MovieLocalListAdapter.Callback callback = new MovieLocalListAdapter.Callback() {
        @Override
        public void onClickMovie(Integer movie_id) {
            if (movie_id != null) {
                openMovie(movie_id);
            }
        }
    };
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        presenter = new MovieListPresenter(prepareDataSource(MovieRemoteDataSource.getInstance(), MovieLocalDataSource.getINSTANCE()), this);

        initView();

        initControllers();

    }

    private void initControllers() {
        adapter = new MovieListAdapter(callbacks);
        rv_list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rv_list.addItemDecoration(new ItemDecorationGrid(0, 2));
        rv_list.setAdapter(adapter);

        localListAdapter = new MovieLocalListAdapter(callback);
        rv_list_local.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_list_local.setAdapter(localListAdapter);



    }

    private void initView() {
        rv_list = findViewById(R.id.rv_list);
        rv_list_local = findViewById(R.id.rv_list_local);
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
    public void showProcessing() {

    }

    @Override
    public void hideProcessing() {

    }

    @Override
    public void setMovies(List<Results> movies) {
        adapter.setMovies(movies);
    }

    @Override
    public void setLocalMovies(List<Movie> movies) {
        localListAdapter.setData(movies);
    }

    private MovieListAdapter.Callbacks callbacks = new MovieListAdapter.Callbacks() {
        @Override
        public void onItemClick(Results result) {
            openMovie(result);
        }
    };

    private void openMovie(Results result) {

        Intent openMovie = new Intent(MovieList.this, MovieDetail.class);
        openMovie.putExtra(ClickedData_TAG, String.valueOf(result.getId()));
        startActivity(openMovie);
    }


    private void openMovie(Integer movie_id) {
        Intent openMovie = new Intent(MovieList.this, MovieDetail.class);
        openMovie.putExtra(ClickedData_TAG, String.valueOf(movie_id));
        startActivity(openMovie);
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

        if (!isConnected) {
            presenter.loadLocalMovies();
            onNetworkDisconnected();
            snackbar = Snackbar.make(findViewById(android.R.id.content), "No Internet Connection", Snackbar.LENGTH_INDEFINITE);
            snackbar.show();
        } else {
            onNetworkConnected();
            presenter.loadNowPlaying();
            snackbar = Snackbar.make(findViewById(android.R.id.content), "Internet Connected", Snackbar.LENGTH_SHORT);
            snackbar.setText("Internet Connected");
            snackbar.show();
            rv_list.smoothScrollToPosition(0);
        }

    }

    private void onNetworkConnected() {
        rv_list_local.setVisibility(View.GONE);
        rv_list.setVisibility(View.VISIBLE);
    }

    private void onNetworkDisconnected() {
        rv_list_local.setVisibility(View.VISIBLE);
        rv_list.setVisibility(View.GONE);
    }
}

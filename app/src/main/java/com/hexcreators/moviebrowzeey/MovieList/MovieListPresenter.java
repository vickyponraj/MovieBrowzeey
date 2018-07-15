package com.hexcreators.moviebrowzeey.MovieList;

import android.util.Log;

import com.hexcreators.moviebrowzeey.Data.Model.Results;
import com.hexcreators.moviebrowzeey.Data.MovieDataSource;
import com.hexcreators.moviebrowzeey.Data.MovieRepository;

import java.util.List;

public class MovieListPresenter implements MovieListContract.Presenter {

    private static final String TAG = MovieListPresenter.class.getSimpleName();
    private MovieListContract.View mView;

    private MovieRepository mMovieRepository;

    public MovieListPresenter(MovieRepository movieRepository, MovieListContract.View view) {
        mView = view;
        mMovieRepository = movieRepository;
        loadNowPlaying();
    }

    private void loadNowPlaying() {
        mMovieRepository.getMovies(new MovieDataSource.LoadMoviesCallBack() {
            @Override
            public void onSuccess(List<Results> movies) {
                Log.d(TAG,"loadNowPlaying");
                mView.setMovies(movies);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }


}

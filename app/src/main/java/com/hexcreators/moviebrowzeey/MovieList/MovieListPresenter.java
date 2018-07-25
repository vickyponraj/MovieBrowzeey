package com.hexcreators.moviebrowzeey.MovieList;

import android.util.Log;

import com.hexcreators.moviebrowzeey.Data.Model.Movie;
import com.hexcreators.moviebrowzeey.Data.Model.Results;
import com.hexcreators.moviebrowzeey.Data.MovieDataSource;
import com.hexcreators.moviebrowzeey.Data.MovieRepository;

import java.util.List;

import static com.hexcreators.moviebrowzeey.Z_AppUtills.AppUtills.INVALID_API;

public class MovieListPresenter implements MovieListContract.Presenter {

    private static final String TAG = MovieListPresenter.class.getSimpleName();
    private MovieListContract.View mView;

    private MovieRepository mMovieRepository;

    public MovieListPresenter(MovieRepository movieRepository, MovieListContract.View view) {
        mView = view;
        mMovieRepository = movieRepository;
    }

    public void loadNowPlaying() {
        mMovieRepository.getMovies(new MovieDataSource.LoadMoviesCallBack() {
            @Override
            public void onSuccess(List<Results> movies) {
                Log.d(TAG, "loadNowPlaying");
                mView.setMovies(movies);
            }

            @Override
            public void onFailure(Throwable throwable) {
                boolean b = throwable.getMessage().equalsIgnoreCase(INVALID_API);
                if (b)
                    mView.showErrorMessage(throwable.getMessage());
                else
                    mView.showMessage(throwable.getMessage());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }


    @Override
    public void loadLocalMovies() {
        mMovieRepository.getLocalMovies(new MovieDataSource.LoadLocalMoviesCallBack() {


            @Override
            public void onSuccess(List<Movie> movies) {
                mView.setLocalMovies(movies);
            }

            @Override
            public void onFailure(Throwable throwable) {
                boolean b = throwable.getMessage().equalsIgnoreCase(INVALID_API);
                if (b)
                    mView.showErrorMessage(throwable.getMessage());
                else
                    mView.showMessage(throwable.getMessage());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}

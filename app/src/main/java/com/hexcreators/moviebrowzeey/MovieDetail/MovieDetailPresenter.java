package com.hexcreators.moviebrowzeey.MovieDetail;

import com.hexcreators.moviebrowzeey.Data.Model.Movie;
import com.hexcreators.moviebrowzeey.Data.MovieDataSource;
import com.hexcreators.moviebrowzeey.Data.MovieRepository;

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private static final String TAG = MovieDetailPresenter.class.getSimpleName();
    private MovieDetailContract.View mView;
    private MovieRepository mMovieRepository;


    public MovieDetailPresenter(MovieDetailContract.View mView, MovieRepository mMovieRepository) {
        this.mView = mView;
        this.mMovieRepository = mMovieRepository;
    }

    public void loadMovieDetails(int movieID) {

        mView.showProcessing();

        mMovieRepository.getMovie(movieID, new MovieDataSource.LoadMovieCallBack() {
            @Override
            public void onSuccess(Movie movie) {
                mView.hideProcessing();
                mView.showMovie(movie);
            }

            @Override
            public void onFailure(Throwable throwable) {
                mView.hideProcessing();
            }

            @Override
            public void onDataNotAvailable() {
                mView.hideProcessing();
            }
        });

    }
}

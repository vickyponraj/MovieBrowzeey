package com.hexcreators.moviebrowzeey.MovieDetail;

import com.hexcreators.moviebrowzeey.Data.Model.Movie;
import com.hexcreators.moviebrowzeey.Data.Model.Results;
import com.hexcreators.moviebrowzeey.Data.MovieDataSource;
import com.hexcreators.moviebrowzeey.Data.MovieRepository;

import java.util.List;

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private static final String TAG = MovieDetailPresenter.class.getSimpleName();
    private MovieDetailContract.View mView;
    private MovieRepository mMovieRepository;
    private int movieID;


    public MovieDetailPresenter(MovieDetailContract.View mView, MovieRepository mMovieRepository) {
        this.mView = mView;
        this.mMovieRepository = mMovieRepository;
    }

    public void loadMovieDetails(int movieID) {

        this.movieID = movieID;

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

    @Override
    public void loadSimilar(Integer id) {
        mView.showSimilarProcessing();

        mMovieRepository.getMovieSuggestions(id, new MovieDataSource.LoadMoviesCallBack() {
            @Override
            public void onSuccess(List<Results> movies) {
                mView.hideSimilarProcessing();
                mView.setSimilarMovies(movies);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
}

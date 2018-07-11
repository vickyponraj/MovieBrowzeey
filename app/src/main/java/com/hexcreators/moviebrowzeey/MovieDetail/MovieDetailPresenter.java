package com.hexcreators.moviebrowzeey.MovieDetail;

import com.hexcreators.moviebrowzeey.Data.MovieRepository;

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private MovieDetailContract.View mView;

    private MovieRepository mMovieRepository;

    public MovieDetailPresenter(MovieRepository movieRepository, MovieDetailContract.View view) {
        mView = view;
        mMovieRepository = movieRepository;
    }



}

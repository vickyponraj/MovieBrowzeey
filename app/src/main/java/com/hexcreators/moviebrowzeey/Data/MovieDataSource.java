package com.hexcreators.moviebrowzeey.Data;

import android.support.annotation.NonNull;

import com.hexcreators.moviebrowzeey.Data.Model.Movie;

import java.util.List;

public interface MovieDataSource {

    interface LoadMovieCallBack{

        void onSuccess(Movie movie);

        void onFailure(Throwable throwable);

        void onDataNotAvailable();

    }

    interface LoadMoviesCallBack{

        void onSuccess(List<Movie> movies);

        void onFailure(Throwable throwable);

        void onDataNotAvailable();

    }


    void getMovie(@NonNull Integer movieId,@NonNull LoadMovieCallBack callBack);

    void getMovies(@NonNull LoadMoviesCallBack callBack);

}

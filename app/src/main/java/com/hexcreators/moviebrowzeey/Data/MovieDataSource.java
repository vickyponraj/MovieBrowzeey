package com.hexcreators.moviebrowzeey.Data;

import android.support.annotation.NonNull;

import com.hexcreators.moviebrowzeey.Data.Model.Movie;
import com.hexcreators.moviebrowzeey.Data.Model.Results;

import java.util.List;

public interface MovieDataSource {

    void getMovieSuggestions(@NonNull Integer id,@NonNull LoadMoviesCallBack callBack);

    void getLocalMovies(@NonNull LoadLocalMoviesCallBack loadLocalMoviesCallBack);

    void addMovie(Movie movie);

    interface LoadMovieCallBack{

        void onSuccess(Movie movie);

        void onFailure(Throwable throwable);

        void onDataNotAvailable();

    }

    interface LoadMoviesCallBack{

        void onSuccess(List<Results> movies);

        void onFailure(Throwable throwable);

        void onDataNotAvailable();

    }

    interface LoadLocalMoviesCallBack{

        void onSuccess(List<Movie> movies);

        void onFailure(Throwable throwable);

        void onDataNotAvailable();

    }


    void getMovie(@NonNull Integer movieId,@NonNull LoadMovieCallBack callBack);

    void getMovies(@NonNull LoadMoviesCallBack callBack);

}

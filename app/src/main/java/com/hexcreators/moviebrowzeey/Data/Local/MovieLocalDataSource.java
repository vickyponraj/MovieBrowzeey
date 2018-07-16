package com.hexcreators.moviebrowzeey.Data.Local;

import android.support.annotation.NonNull;

import com.hexcreators.moviebrowzeey.Data.MovieDataSource;

public class MovieLocalDataSource implements MovieDataSource {


    private static  MovieLocalDataSource INSTANCE = null;



    public static  MovieLocalDataSource getINSTANCE(){
        if(INSTANCE ==  null){
            INSTANCE    =   new MovieLocalDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void getMovieSuggestions(@NonNull Integer id, @NonNull LoadMoviesCallBack callBack) {

    }

    @Override
    public void getMovie(@NonNull Integer movieId, @NonNull LoadMovieCallBack callBack) {

    }

    @Override
    public void getMovies(@NonNull LoadMoviesCallBack callBack) {

    }
}

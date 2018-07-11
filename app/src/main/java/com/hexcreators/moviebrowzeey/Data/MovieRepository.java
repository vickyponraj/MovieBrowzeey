package com.hexcreators.moviebrowzeey.Data;

import android.support.annotation.NonNull;

public class MovieRepository implements MovieDataSource{

    private static MovieRepository INSTANCE = null;

    @Override
    public void getMovie(@NonNull Integer movieId, @NonNull LoadMovieCallBack callBack) {

    }

    @Override
    public void getMovies(@NonNull LoadMoviesCallBack callBack) {

    }
}

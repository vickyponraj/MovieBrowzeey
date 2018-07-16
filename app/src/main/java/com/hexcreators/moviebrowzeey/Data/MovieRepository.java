package com.hexcreators.moviebrowzeey.Data;

import android.support.annotation.NonNull;

import com.hexcreators.moviebrowzeey.Data.Local.MovieLocalDataSource;
import com.hexcreators.moviebrowzeey.Data.Remote.MovieRemoteDataSource;

public class MovieRepository implements MovieDataSource {

    private static MovieRepository INSTANCE = null;
    private final MovieRemoteDataSource movieRemoteDataSource;
    private final MovieLocalDataSource movieLocalDataSource;

    private MovieRepository(MovieRemoteDataSource movieRemoteDataSource, MovieLocalDataSource movieLocalDataSource) {
        this.movieRemoteDataSource = movieRemoteDataSource;
        this.movieLocalDataSource = movieLocalDataSource;
    }


    @Override
    public void getMovieSuggestions(@NonNull Integer id, @NonNull LoadMoviesCallBack callBack) {
        if (INSTANCE == null) {
            callBack.onFailure(new Exception("MovieRepository NullPointer Exception"));
        }
        if (movieRemoteDataSource == null) {
            callBack.onFailure(new Exception("MovieRemoteDataSource NullPointer Exception"));
        }
        if (movieLocalDataSource == null) {
            callBack.onFailure(new Exception("MovieLocalDataSource NullPointer Exception"));
        }
        movieRemoteDataSource.getMovieSuggestions(id,callBack);
    }

    @Override
    public void getMovie(@NonNull Integer movieId, @NonNull LoadMovieCallBack callBack) {
        if (INSTANCE == null) {
            callBack.onFailure(new Exception("MovieRepository NullPointer Exception"));
        }
        if (movieRemoteDataSource == null) {
            callBack.onFailure(new Exception("MovieRemoteDataSource NullPointer Exception"));
        }
        if (movieLocalDataSource == null) {
            callBack.onFailure(new Exception("MovieLocalDataSource NullPointer Exception"));
        }
        movieRemoteDataSource.getMovie(movieId,callBack);
    }

    @Override
    public void getMovies(@NonNull LoadMoviesCallBack callBack) {
        if (INSTANCE == null) {
            callBack.onFailure(new Exception("MovieRepository NullPointer Exception"));
            return;
        }
        if (movieRemoteDataSource == null) {
            callBack.onFailure(new Exception("MovieRemoteDataSource NullPointer Exception"));
            return;

        }
        if (movieLocalDataSource == null) {
            callBack.onFailure(new Exception("MovieLocalDataSource NullPointer Exception"));
            return;
        }

        movieRemoteDataSource.getMovies(callBack);



    }


    public static MovieRepository getINSTANCE(MovieRemoteDataSource movieRemoteDataSource, MovieLocalDataSource movieLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MovieRepository(movieRemoteDataSource, movieLocalDataSource);
        }
        return INSTANCE;
    }







    /*public static TasksRepository getInstance(TasksDataSource tasksRemoteDataSource,
                                              TasksDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }*/


}

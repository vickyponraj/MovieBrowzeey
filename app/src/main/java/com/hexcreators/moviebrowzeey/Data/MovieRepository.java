package com.hexcreators.moviebrowzeey.Data;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hexcreators.moviebrowzeey.Data.Local.MovieLocalDataSource;
import com.hexcreators.moviebrowzeey.Data.Model.Movie;
import com.hexcreators.moviebrowzeey.Data.Remote.MovieRemoteDataSource;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static android.support.v4.util.Preconditions.checkNotNull;

public class MovieRepository implements MovieDataSource {

    private static MovieRepository INSTANCE = null;
    private final MovieDataSource movieRemoteDataSource;
    private final MovieDataSource movieLocalDataSource;
    private Map<Integer, Movie> mCachedMovies;

    private MovieRepository(MovieDataSource movieRemoteDataSource, MovieDataSource movieLocalDataSource) {
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
        Objects.requireNonNull(movieRemoteDataSource).getMovieSuggestions(id, callBack);
    }

    @Override
    public void getLocalMovies(@NonNull LoadLocalMoviesCallBack loadLocalMoviesCallBack) {
        if (INSTANCE == null) {
            loadLocalMoviesCallBack.onFailure(new Exception("MovieRepository NullPointer Exception"));
        }
        if (movieRemoteDataSource == null) {
            loadLocalMoviesCallBack.onFailure(new Exception("MovieRemoteDataSource NullPointer Exception"));
        }
        if (movieLocalDataSource == null) {
            loadLocalMoviesCallBack.onFailure(new Exception("MovieLocalDataSource NullPointer Exception"));
        }

        Objects.requireNonNull(movieLocalDataSource).getLocalMovies(loadLocalMoviesCallBack);

        //movieRemoteDataSource.getMovieSuggestions(id, callBack);
    }

    @Override
    public void addMovie(Movie movie) {

    }

    @Override
    public void getMovie(@NonNull final Integer movieId, @NonNull final LoadMovieCallBack callBack) {
        if (INSTANCE == null) {
            callBack.onFailure(new Exception("MovieRepository NullPointer Exception"));
        }
        if (movieRemoteDataSource == null) {
            callBack.onFailure(new Exception("MovieRemoteDataSource NullPointer Exception"));
        }
        if (movieLocalDataSource == null) {
            callBack.onFailure(new Exception("MovieLocalDataSource NullPointer Exception"));
        }

        Movie localDbMovie = getMovieWithId(movieId);

        if (localDbMovie != null) {
            callBack.onSuccess(localDbMovie);
            return;
        }

        movieLocalDataSource.getMovie(movieId, new LoadMovieCallBack() {
            @Override
            public void onSuccess(Movie movie) {
                callBack.onSuccess(movie);
                updateCache(movie);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callBack.onFailure(throwable);
            }

            @Override
            public void onDataNotAvailable() {
                movieRemoteDataSource.getMovie(movieId, new LoadMovieCallBack() {
                    @Override
                    public void onSuccess(Movie movie) {
                        updateDB(movie);
                        callBack.onSuccess(movie);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        callBack.onFailure(throwable);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        callBack.onDataNotAvailable();
                    }
                });
            }
        });


    }

    private void updateDB(Movie movie) {

        movieLocalDataSource.addMovie(movie);

        if (mCachedMovies == null) {
            mCachedMovies = new LinkedHashMap<>();
        }

        if (movie != null && movie.getId() != null)
            mCachedMovies.put(movie.getId(), movie);
    }
    private void updateCache(Movie movie) {

        if (mCachedMovies == null) {
            mCachedMovies = new LinkedHashMap<>();
        }

        if (movie != null && movie.getId() != null)
            mCachedMovies.put(movie.getId(), movie);
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


    @SuppressLint("RestrictedApi")
    @Nullable
    private Movie getMovieWithId(@NonNull Integer id) {
        checkNotNull(id);
        if (mCachedMovies == null || mCachedMovies.isEmpty()) {
            return null;
        } else {
            return mCachedMovies.get(id);
        }
    }

    /*public static TasksRepository getInstance(TasksDataSource tasksRemoteDataSource,
                                              TasksDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }*/


}

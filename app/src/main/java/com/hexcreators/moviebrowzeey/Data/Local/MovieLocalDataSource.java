package com.hexcreators.moviebrowzeey.Data.Local;

import android.support.annotation.NonNull;

import com.hexcreators.moviebrowzeey.Data.Model.Movie;
import com.hexcreators.moviebrowzeey.Data.MovieDataSource;

import io.realm.Realm;
import io.realm.RealmResults;

public class MovieLocalDataSource implements MovieDataSource {


    private static MovieLocalDataSource INSTANCE = null;
    private static Realm realm = null;


    public static MovieLocalDataSource getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MovieLocalDataSource();
            realm = Realm.getDefaultInstance();
        }
        return INSTANCE;
    }


    @Override
    public void getMovieSuggestions(@NonNull Integer id, @NonNull LoadMoviesCallBack callBack) {

    }

    @Override
    public void getLocalMovies(@NonNull LoadLocalMoviesCallBack loadLocalMoviesCallBack) {
        RealmResults<Movie> all = realm.where(Movie.class).findAll();
        if (realm != null && all != null) {
            if (all.size() <= 0) {
                loadLocalMoviesCallBack.onDataNotAvailable();
            } else {
                loadLocalMoviesCallBack.onSuccess(all);
            }
        } else {
            loadLocalMoviesCallBack.onFailure(new Exception("Error Realm "));
        }
    }

    @Override
    public void addMovie(Movie movie) {

        if (INSTANCE != null && realm != null) {
            realm.beginTransaction();
            realm.copyToRealm(movie);
            realm.commitTransaction();
        }

    }

    @Override
    public void getMovie(@NonNull Integer movieId, @NonNull LoadMovieCallBack callBack) {
        if (INSTANCE != null && realm != null) {
            Movie movie = realm.where(Movie.class).equalTo("id", movieId).findFirst();
            if (movie != null) {
                callBack.onSuccess(movie);
            } else {
                callBack.onDataNotAvailable();
            }
        } else {
            callBack.onDataNotAvailable();
        }
    }

    @Override
    public void getMovies(@NonNull LoadMoviesCallBack callBack) {

    }
}

package com.hexcreators.moviebrowzeey.Data.Remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.hexcreators.moviebrowzeey.Data.Model.Movie;
import com.hexcreators.moviebrowzeey.Data.Model.NowPlayingResponse;
import com.hexcreators.moviebrowzeey.Data.Model.Results;
import com.hexcreators.moviebrowzeey.Data.MovieDataSource;
import com.hexcreators.moviebrowzeey.Z_AppUtills.RetrofitBase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRemoteDataSource implements MovieDataSource {

    private static final String TAG = MovieRemoteDataSource.class.getSimpleName();
    private static MovieRemoteDataSource INSTANCE = null;

    @Override
    public void getMovieSuggestions(@NonNull Integer id, @NonNull final LoadMoviesCallBack callBack) {
        MovieDataApiService movieDataApiService = RetrofitBase.getRetrofitInstance().create(MovieDataApiService.class);

        Call<NowPlayingResponse> nowPlayingResponse = movieDataApiService.getSimilarMovies(id,getApiKey());

        nowPlayingResponse.enqueue(new Callback<NowPlayingResponse>() {
            @Override
            public void onResponse(@NonNull Call<NowPlayingResponse> call, @NonNull Response<NowPlayingResponse> response) {
                Log.d(TAG, "getMovies onResponse");

                List<Results> results = response.body().getResults();
                if (results != null) {
                    Log.d(TAG, "getMovies != null");
                    callBack.onSuccess(results);
                } else {
                    Log.d(TAG, "getMovies == null");
                }
            }

            @Override
            public void onFailure(Call<NowPlayingResponse> call, Throwable t) {
                Log.d(TAG, "getMovies onFailure");
                Log.d(TAG, t.getMessage());

                callBack.onFailure(t);
            }
        });
    }

    @Override
    public void getLocalMovies(@NonNull LoadLocalMoviesCallBack loadLocalMoviesCallBack) {

    }

    @Override
    public void addMovie(Movie movie) {

    }

    @Override
    public void getMovie(@NonNull final Integer movieId, @NonNull final LoadMovieCallBack callBack) {
        MovieDataApiService movieDataApiService = RetrofitBase.getRetrofitInstance().create(MovieDataApiService.class);

        Call<Movie> movie = movieDataApiService.getMovie(movieId, getApiKey());

        movie.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d(TAG, "getMovie " + movieId + " onFailure");
                Log.d(TAG, t.getMessage());
                callBack.onFailure(t);
            }
        });

    }

    @Override
    public void getMovies(@NonNull final LoadMoviesCallBack callBack) {
        MovieDataApiService movieDataApiService = RetrofitBase.getRetrofitInstance().create(MovieDataApiService.class);

        Call<NowPlayingResponse> nowPlayingResponse = movieDataApiService.getNowPlayingResponse(getApiKey());

        nowPlayingResponse.enqueue(new Callback<NowPlayingResponse>() {
            @Override
            public void onResponse(@NonNull Call<NowPlayingResponse> call, @NonNull Response<NowPlayingResponse> response) {
                Log.d(TAG, "getMovies onResponse");

                List<Results> results = response.body().getResults();
                if (results != null) {
                    Log.d(TAG, "getMovies != null");
                    callBack.onSuccess(results);
                } else {
                    Log.d(TAG, "getMovies == null");
                }
            }

            @Override
            public void onFailure(Call<NowPlayingResponse> call, Throwable t) {
                Log.d(TAG, "getMovies onFailure");
                Log.d(TAG, t.getMessage());

                callBack.onFailure(t);
            }
        });
    }

    public static MovieRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MovieRemoteDataSource();
        }
        return INSTANCE;
    }

    public String getApiKey() {
        return "";
    }
}

package com.hexcreators.moviebrowzeey.Data.Remote;

import com.hexcreators.moviebrowzeey.Data.Model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDataApi {

    @GET("movie/{movie_id}")
    Call<Movie> getMovie(@Path("movie_id") int movieId, @Query("api_key") String api_key);

}

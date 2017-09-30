package com.example.shrinivas.myhelloworldapplication.rest;

import com.example.shrinivas.myhelloworldapplication.model.Movie;
import com.example.shrinivas.myhelloworldapplication.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("movie/upcoming")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}
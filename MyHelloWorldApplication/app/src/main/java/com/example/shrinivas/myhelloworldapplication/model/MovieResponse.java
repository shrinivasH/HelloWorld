package com.example.shrinivas.myhelloworldapplication.model;

import com.google.gson.annotations.SerializedName;

public class MovieResponse {
    @SerializedName("results")
    Movie[]results;

    public MovieResponse(Movie[] results) {
        this.results = results;
    }

    public Movie[] getResults() {
        return results;
    }

    public void setResults(Movie[] results) {
        this.results = results;
    }
}
package com.example.shrinivas.myhelloworldapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResponse {
    @SerializedName("results")
    Movie[]results;

    public MovieResponse(Movie[] results) {
        this.results = results;
    }

    /*public Movie[] getResults() {
        return results;
    }*/
    public ArrayList<Movie> getResults() {
        ArrayList<Movie> movieResponses = new ArrayList<Movie>();
        for (Movie movie : results) {
            movieResponses.add(movie);
        }
        return movieResponses;
    }
    public void setResults(Movie[] results) {
        this.results = results;
    }
}
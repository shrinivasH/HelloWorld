package com.example.shrinivas.myhelloworldapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.shrinivas.myhelloworldapplication.R;
import com.example.shrinivas.myhelloworldapplication.adpater.MovieAdapter;
import com.example.shrinivas.myhelloworldapplication.model.Movie;
import com.example.shrinivas.myhelloworldapplication.model.MovieResponse;
import com.example.shrinivas.myhelloworldapplication.rest.ApiClient;
import com.example.shrinivas.myhelloworldapplication.rest.ApiInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerList);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ApiInterface apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        mProgressBar.setVisibility(View.VISIBLE);
        Call<MovieResponse> call = apiInterface.getTopRatedMovies("");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                int statusCode = response.code();
                Movie[] movieResponses = response.body().getResults();
                ArrayList<Movie> movieResponses1 = new ArrayList<Movie>();
                for (Movie movie : movieResponses) {
                    movieResponses1.add(movie);
                }
                movieResponses1.size();
                MovieAdapter movieAdapter = new MovieAdapter(movieResponses1, getApplicationContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(movieAdapter);
                mProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("Error", t.toString());
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

    }
}

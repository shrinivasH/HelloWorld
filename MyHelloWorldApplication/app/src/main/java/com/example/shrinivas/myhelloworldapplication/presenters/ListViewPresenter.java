package com.example.shrinivas.myhelloworldapplication.presenters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.shrinivas.myhelloworldapplication.activities.ListViewActivity;
import com.example.shrinivas.myhelloworldapplication.adpater.MovieAdapter;
import com.example.shrinivas.myhelloworldapplication.model.Movie;
import com.example.shrinivas.myhelloworldapplication.model.MovieResponse;
import com.example.shrinivas.myhelloworldapplication.provider.BusProvider;
import com.example.shrinivas.myhelloworldapplication.rest.ApiClient;
import com.example.shrinivas.myhelloworldapplication.rest.ApiInterface;
import com.squareup.otto.Bus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewPresenter {
    private ApiInterface apiInterface;
    private Bus bus;
    private Context mContext;

    public ListViewPresenter(Context mContext) {
        this.apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        this.mContext = mContext;
        bus = BusProvider.instantiationBus();
    }

    public void registerEventBus() {
        bus.register(mContext);
    }

    public void unRegisterBus() {
        bus.unregister(mContext);
    }

    public void getListViewData() {
        Call<MovieResponse> call = apiInterface.getTopRatedMovies("b7cd3340a794e5a2f35e3abb820b497f");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                int statusCode = response.code();
                ArrayList<Movie> movieResponses = response.body().getResults();
                bus.post(movieResponses);

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("Error", t.toString());

            }
        });
    }
}
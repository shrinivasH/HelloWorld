package com.example.shrinivas.myhelloworldapplication.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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
import com.example.shrinivas.myhelloworldapplication.presenters.ListViewPresenter;
import com.example.shrinivas.myhelloworldapplication.rest.ApiClient;
import com.example.shrinivas.myhelloworldapplication.rest.ApiInterface;
import com.squareup.otto.Subscribe;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    private ListViewPresenter mListViewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerList);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBarHandler(this);
        showProgressBar();
        mListViewPresenter = new ListViewPresenter(this);
        mListViewPresenter.getListViewData();
    }

    @Subscribe
    public void getMessage(ArrayList<Movie> movieResponses) {

        MovieAdapter movieAdapter = new MovieAdapter(movieResponses, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(movieAdapter);
        hideProgressBar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mListViewPresenter.registerEventBus();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mListViewPresenter.unRegisterBus();
    }
}

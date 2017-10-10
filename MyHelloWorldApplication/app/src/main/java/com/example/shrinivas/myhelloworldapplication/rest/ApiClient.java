package com.example.shrinivas.myhelloworldapplication.rest;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static Retrofit retrofit = null;


    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
             /*
            * to see request and response on log*/
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            /*
            * For read and write time out is set her*/
            OkHttpClient.Builder b = new OkHttpClient.Builder();
            b.readTimeout(5000, TimeUnit.MILLISECONDS);
            b.writeTimeout(5000, TimeUnit.MILLISECONDS);
            b.connectTimeout(7000, TimeUnit.MILLISECONDS);
            b.addInterceptor(interceptor);


            OkHttpClient client = b.build();


            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }
}
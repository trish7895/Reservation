package com.thiman.android.reservationmanager.rest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    static Context context;
    public ApiClient(Context context){
        ApiClient.context = context;
    }

    public static final String BASE_URL = "http://10.0.2.2:3002/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient() {
//        SharedPreferences reservationSettings = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor reservationSettingsEditor = reservationSettings.edit();
//        final String accessToken=reservationSettings.getString("access_token","token is empty");

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("x-access-token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Im1hbmFnZXIiLCJpYXQiOjE1MTc0ODUzNjMsImV4cCI6MTUxNzU3MTc2M30.Q5ggLd1XUMtMLdDFDH9Nl8Mg4Y7wulpYUNJ7-24NBVk")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build();
        }
        return retrofit;
    }
}

package com.example.idillika2.Api;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static final String BASE_URL = "https://idillika.com/api/";
    private static IdillikaApi idillikaApi;

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        idillikaApi = retrofit.create(IdillikaApi.class);
    }

    public static IdillikaApi getIdillikaApi() {
        return idillikaApi;
    }
}

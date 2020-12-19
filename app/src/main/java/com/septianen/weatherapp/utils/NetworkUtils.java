package com.septianen.weatherapp.utils;

import android.content.Context;
import android.util.Log;


import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.septianen.weatherapp.data.ConstData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Utility to handle every network function
 */

public final class NetworkUtils {

    public static final String TAG = "networkUtils";

    public NetworkUtils() {

    }

    /**
     * Retrofit builder ...
     * ... it calls when need to hit an API ...
     * ... HttpLoggingInterceptor used to log every response ...
     * ... ChuckerInterceptor used to show response while using the app
     *
     * @param context
     * @return
     */
    public static Retrofit buildRetrofit(Context context) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // uncomment to enable chucker
        ChuckerInterceptor chuckerInterceptor = new ChuckerInterceptor(context);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(chuckerInterceptor) // uncomment to enable chucker
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ConstData.API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }



}

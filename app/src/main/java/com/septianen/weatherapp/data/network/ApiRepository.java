package com.septianen.weatherapp.data.network;

import android.content.Context;

import com.septianen.weatherapp.data.AppError;
import com.septianen.weatherapp.data.ConstData;
import com.septianen.weatherapp.data.model.CityResponse;
import com.septianen.weatherapp.data.model.ForecastResponse;
import com.septianen.weatherapp.utils.NetworkUtils;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * ApiRepository to retrieve data from network
 */

public class ApiRepository implements ApiListener {

    private Retrofit retrofit;

    private ApiService apiService;

    public ApiRepository(Context context) {

        retrofit = NetworkUtils.buildRetrofit(context);
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public void getCities(String idBundle, OnGetCities listener) {

        // show loading animation
        listener.onProgress();

        Call<CityResponse> call = apiService.getCities(idBundle, ConstData.API.API_KEY);
        call.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, retrofit2.Response<CityResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body().getCities());
                } else {
                    listener.onFailed(new AppError(response.code(), response.message()));
                }
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                listener.onFailed(new AppError(500, t.getMessage()));
            }
        });
    }

    @Override
    public void getForecasts(Integer id, OnGetForecasts listener) {

        // show loading animation
        listener.onProgress();

        Call<ForecastResponse> call = apiService.getForecasts(id, ConstData.API.API_KEY, 8);
        call.enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, retrofit2.Response<ForecastResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body().getForecasts());
                } else {
                    listener.onFailed(new AppError(response.code(), response.message()));
                }
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                listener.onFailed(new AppError(500, t.getMessage()));
            }
        });
    }
}

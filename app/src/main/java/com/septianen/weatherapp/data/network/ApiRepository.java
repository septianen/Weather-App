package com.septianen.weatherapp.data.network;

import android.content.Context;

import com.septianen.weatherapp.data.AppError;
import com.septianen.weatherapp.data.ConstData;
import com.septianen.weatherapp.data.model.CityResponse;
import com.septianen.weatherapp.utils.NetworkUtils;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class ApiRepository implements ApiListener {

    private Context context;
    private Retrofit retrofit;



    public ApiRepository(Context context) {
        this.context = context;

        retrofit = NetworkUtils.buildRetrofit(context);
    }

    @Override
    public void getCities(String idBundle, OnGetCities listener) {

        ApiService apiService = retrofit.create(ApiService.class);

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


    }
}

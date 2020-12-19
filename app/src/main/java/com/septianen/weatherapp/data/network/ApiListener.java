package com.septianen.weatherapp.data.network;

import com.septianen.weatherapp.data.AppError;
import com.septianen.weatherapp.data.model.City;
import com.septianen.weatherapp.data.model.Forecast;

import java.util.List;

public interface ApiListener {

    void getCities(String idBundle, OnGetCities listener);

    void getForecasts(Integer id, OnGetForecasts listener);


    /**
     * Callback listener ...
     * ... OnGetcities ...
     * ... OnGetForecasts ...
     */
    interface OnGetCities {
        void onProgress();

        void onSuccess(List<City> cities);

        void onFailed(AppError appError);
    }

    interface OnGetForecasts {
        void onProgress();

        void onSuccess(List<Forecast> forecasts);

        void onFailed(AppError appError);
    }
}

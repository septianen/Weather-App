package com.septianen.weatherapp.ui.city;

import com.septianen.weatherapp.data.AppError;
import com.septianen.weatherapp.data.model.City;
import com.septianen.weatherapp.data.network.ApiListener;

import java.util.List;

public interface CityMvp {

    interface View {
        void onProgress();

        void onSuccess(List<City> cities);

        void onFailed(AppError appError);
    }

    interface Presenter {
        void getCities();
    }
}

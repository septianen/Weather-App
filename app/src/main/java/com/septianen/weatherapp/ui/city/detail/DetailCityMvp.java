package com.septianen.weatherapp.ui.city.detail;

import com.septianen.weatherapp.data.AppError;
import com.septianen.weatherapp.data.model.Forecast;

import java.util.List;

public interface DetailCityMvp {

    interface View {
        void onProgress();

        void onSuccess(List<Forecast> forecasts);

        void onFailed(AppError appError);
    }

    interface Presenter {
        void getForecasts(Integer id);
    }
}

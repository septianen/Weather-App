package com.septianen.weatherapp.ui.city.detail;

import com.septianen.weatherapp.data.AppError;
import com.septianen.weatherapp.data.model.Forecast;
import com.septianen.weatherapp.data.network.ApiListener;
import com.septianen.weatherapp.data.network.ApiRepository;

import java.util.List;

public class DetailCityPresenter implements DetailCityMvp.Presenter {

    private DetailCityMvp.View view;
    private ApiRepository apiRepository;

    public DetailCityPresenter(DetailCityMvp.View view, ApiRepository apiRepository) {
        this.view = view;
        this.apiRepository = apiRepository;
    }

    @Override
    public void getForecasts(Integer id) {
        apiRepository.getForecasts(id, new ApiListener.OnGetForecasts() {
            @Override
            public void onProgress() {
                view.onProgress();
            }

            @Override
            public void onSuccess(List<Forecast> forecasts) {
                view.onSuccess(forecasts);
            }

            @Override
            public void onFailed(AppError appError) {
                view.onFailed(appError);
            }
        });
    }
}

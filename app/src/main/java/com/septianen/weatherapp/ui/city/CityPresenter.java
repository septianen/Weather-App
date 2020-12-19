package com.septianen.weatherapp.ui.city;

import com.septianen.weatherapp.data.AppError;
import com.septianen.weatherapp.data.model.City;
import com.septianen.weatherapp.data.network.ApiListener;
import com.septianen.weatherapp.data.network.ApiRepository;

import java.util.List;

public class CityPresenter implements CityMvp.Presenter{

    private CityMvp.View view;
    private ApiRepository apiRepository;

    public CityPresenter(CityMvp.View view, ApiRepository apiRepository) {
        this.view = view;
        this.apiRepository = apiRepository;
    }

    @Override
    public void getCities() {
        String idBundle = "1621500,1621459,1621520";

        apiRepository.getCities(idBundle, new ApiListener.OnGetCities() {
            @Override
            public void onProgress() {
                view.onProgress();
            }

            @Override
            public void onSuccess(List<City> cities) {
                view.onSuccess(cities);
            }

            @Override
            public void onFailed(AppError appError) {
                view.onFailed(appError);
            }
        });
    }
}

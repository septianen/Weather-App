package com.septianen.weatherapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastResponse {
    @SerializedName("list")
    private List<Forecast> forecasts;

    public List<Forecast> getForecasts() {
        return forecasts;
    }
}

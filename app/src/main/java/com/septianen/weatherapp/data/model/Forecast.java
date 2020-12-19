package com.septianen.weatherapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {
    @SerializedName("weather")
    private List<Weather> weathers;

    @SerializedName("main")
    private Info info;

    public List<Weather> getWeathers() {
        return weathers;
    }

    public Info getInfo() {
        return info;
    }
}

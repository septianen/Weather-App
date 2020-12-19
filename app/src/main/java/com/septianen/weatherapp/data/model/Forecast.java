package com.septianen.weatherapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {

    @SerializedName("dt_txt")
    private String date;

    @SerializedName("weather")
    private List<Weather> weathers;

    @SerializedName("main")
    private Info info;

    public String getDate() {
        return date;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public Info getInfo() {
        return info;
    }
}

package com.septianen.weatherapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("weather")
    private List<Weather> weathers;

    @SerializedName("main")
    private Info info;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public Info getInfo() {
        return info;
    }
}

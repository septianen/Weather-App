package com.septianen.weatherapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityResponse {

    @SerializedName("list")
    private List<City> cities;

    public List<City> getCities() {
        return cities;
    }
}

package com.septianen.weatherapp.data.model;

import com.google.gson.annotations.SerializedName;

public class Info {
    @SerializedName("temp")
    private Float temp;

    @SerializedName("feels_like")
    private Float feels_like;

    @SerializedName("temp_min")
    private Float temp_min;

    @SerializedName("temp_max")
    private Float temp_max;

    @SerializedName("pressure")
    private Float pressure;

    @SerializedName("sea_level")
    private Float sea_level;

    @SerializedName("grnd_level")
    private Float grnd_level;

    @SerializedName("humidity")
    private Float humidity;

    @SerializedName("temp_kf")
    private Float temp_kf;


    public Float getTemp() {
        return temp;
    }

    public Float getFeels_like() {
        return feels_like;
    }

    public Float getTemp_min() {
        return temp_min;
    }

    public Float getTemp_max() {
        return temp_max;
    }

    public Float getPressure() {
        return pressure;
    }

    public Float getSea_level() {
        return sea_level;
    }

    public Float getGrnd_level() {
        return grnd_level;
    }

    public Float getHumidity() {
        return humidity;
    }

    public Float getTemp_kf() {
        return temp_kf;
    }
}

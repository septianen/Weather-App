package com.septianen.weatherapp.data;

public class ConstData {

    public static class API {
        public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

        public static final String GET_CITIES = BASE_URL + "group";
        public static final String GET_FORECAST = BASE_URL + "forecast";
    }
}

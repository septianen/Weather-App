package com.septianen.weatherapp.data;

public class ConstData {

    public static class API {
        public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

        public static final String API_KEY = "d0bc4c2811aae3e68d9180d0281feae0";

        public static final String GET_CITIES = BASE_URL + "group";
        public static final String GET_FORECAST = BASE_URL + "forecast";
    }
}

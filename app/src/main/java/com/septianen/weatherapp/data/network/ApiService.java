package com.septianen.weatherapp.data.network;

import com.septianen.weatherapp.data.ConstData;
import com.septianen.weatherapp.data.model.CityResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(ConstData.API.GET_CITIES)
    Call<CityResponse> getCities(
            @Query("id") String idBundle,
            @Query("appId") String apiKey
    );
}

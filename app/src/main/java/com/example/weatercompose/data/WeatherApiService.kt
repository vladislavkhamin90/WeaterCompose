package com.example.weatercompose.data

import retrofit2.http.GET
import retrofit2.http.Query

interface  WeatherApiService {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String,
    ):WeatherResponse


    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("days") days: Int = 5,
    ): WeatherResponse
}

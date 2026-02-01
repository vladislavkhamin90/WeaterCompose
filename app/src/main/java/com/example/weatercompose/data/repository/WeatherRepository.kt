package com.example.weatercompose.data.repository

import com.example.weatercompose.API_KEY
import com.example.weatercompose.data.network.WeatherApiService
import com.example.weatercompose.data.model.WeatherResponse

class WeatherRepository(
    private val api: WeatherApiService
) {
    suspend fun getForecast(city: String): WeatherResponse{
        return api.getForecast(
            apiKey = API_KEY,
            city = city,
            days = 5
        )
    }
}
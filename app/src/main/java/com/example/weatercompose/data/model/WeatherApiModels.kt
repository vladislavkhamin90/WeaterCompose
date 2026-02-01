package com.example.weatercompose.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val location: Location,
    val current: CurrentWeather,
    val forecast: Forecast? = null
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    @SerializedName("localtime")
    val localTime: String
)

data class CurrentWeather(
    @SerializedName("temp_c")
    val tempC: Double,

    val condition: Condition,
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: DayForecast,
    val hour: List<HourForecast>
)

data class DayForecast(
    @SerializedName("maxtemp_c")
    val maxTempC: Double,

    @SerializedName("mintemp_c")
    val minTempC: Double,

    val condition: Condition
)

data class HourForecast(
    val time: String,

    @SerializedName("temp_c")
    val tempC: Double,

    val condition: Condition
)
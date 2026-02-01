package com.example.weatercompose

import com.example.weatercompose.data.ForecastDay
import com.example.weatercompose.data.HourForecast
import com.example.weatercompose.data.Model
import com.example.weatercompose.data.WeatherResponse

fun WeatherResponse.toMainCardModel(): Model {
    return Model(
        city = this.location.name,
        lastTime = this.location.localTime,
        currentTemp = "${this.current.tempC.toInt()}°C",
        condition = this.current.condition.text,
        icon = this.current.condition.icon,
        maxTemp = "${this.forecast?.forecastday[0]?.day?.maxTempC}°",
        minTemp = "${this.forecast?.forecastday[0]?.day?.minTempC}°",
        hours = ""
    )
}

fun List<HourForecast>.toHourlyModels(city: String): List<Model> {
    return this.map { hour ->
        Model(
            city = city,
            lastTime = hour.time.split(" ")[1],
            currentTemp = "${hour.tempC.toInt()}°",
            condition = hour.condition.text,
            icon = hour.condition.icon,
            maxTemp = "",
            minTemp = "",
            hours = ""
        )
    }
}

fun List<ForecastDay>.toDailyModels(city: String): List<Model> {
    return this.mapIndexed { index, day ->
        Model(
            city = city,
            lastTime = if (index == 0) "Now" else day.date,
            currentTemp = "",
            condition = day.day.condition.text,
            icon = day.day.condition.icon,
            maxTemp = "${day.day.maxTempC.toInt()}°",
            minTemp = "${day.day.minTempC.toInt()}°",
            hours = ""
        )
    }
}
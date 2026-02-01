package com.example.weatercompose.data


data class Model(
    val city: String,
    val lastTime: String,
    val currentTemp: String,
    val condition: String,
    val icon: String,
    val maxTemp: String,
    val minTemp: String,
    val hours: String
)
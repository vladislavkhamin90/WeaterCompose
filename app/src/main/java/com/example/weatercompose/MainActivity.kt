package com.example.weatercompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.weatercompose.data.Model
import com.example.weatercompose.data.RetrofitInstance
import com.example.weatercompose.screens.MainCard
import com.example.weatercompose.screens.TabLayout

const val API_KEY = "7e1df1c53f17499a825163433252511"

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val city = "Volgograd"
            var mainCardModel by remember { mutableStateOf<Model?>(null) }
            var hourlyModels by remember { mutableStateOf<List<Model>?>(null) }
            var dailyModels by remember { mutableStateOf<List<Model>?>(null) }

            LaunchedEffect(Unit) {
                try {
                    val response = RetrofitInstance.api.getForecast(API_KEY, city, 5)
                    mainCardModel = response.toMainCardModel()
                    val forecastDay = response.forecast?.forecastday?.get(0)
                    hourlyModels = forecastDay?.hour?.toHourlyModels(city)
                    dailyModels = response.forecast?.forecastday?.toDailyModels(city)

                } catch (e: Exception) {
                    Log.d("MainActivity", e.toString())
                    e.printStackTrace()
                }
            }

            Image(
                painter = painterResource(R.drawable.bg),
                contentDescription = "bg",
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.8f),
                contentScale = ContentScale.Crop
            )

            Column {
                MainCard(mainCardModel)
                TabLayout(hourlyModels, dailyModels)
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
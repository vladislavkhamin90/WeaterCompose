package com.example.weatercompose.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatercompose.data.network.RetrofitInstance
import com.example.weatercompose.data.repository.WeatherRepository
import com.example.weatercompose.ui.WeatherViewModel

class ViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(
            WeatherRepository(RetrofitInstance.api)
        ) as T
    }
}
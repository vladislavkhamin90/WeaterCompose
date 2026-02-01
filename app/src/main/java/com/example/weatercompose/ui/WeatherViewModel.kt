package com.example.weatercompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatercompose.data.repository.WeatherRepository
import com.example.weatercompose.data.toDailyModels
import com.example.weatercompose.data.toHourlyModels
import com.example.weatercompose.data.toMainCardModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState(isLoading = true))
    val uiState: StateFlow<UiState> = _uiState

    init {
        loadWeather()
    }

    fun loadWeather(city: String = _uiState.value.city) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                val response = repository.getForecast(city)

                val main = response.toMainCardModel()
                val hourly = response.forecast
                    ?.forecastday
                    ?.firstOrNull()
                    ?.hour
                    ?.toHourlyModels(city)
                    ?: emptyList()

                val daily = response.forecast
                    ?.forecastday
                    ?.toDailyModels(city)
                    ?: emptyList()

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        city = city,
                        mainCard = main,
                        hourly = hourly,
                        daily = daily
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun refresh() {
        loadWeather()
    }

    fun changeCity(newCity: String) {
        loadWeather(newCity)
    }

    fun openCityDialog() {
        _uiState.update {
            it.copy(isCityDialogVisible = true)
        }
    }

    fun closeCityDialog() {
        _uiState.update {
            it.copy(isCityDialogVisible = false)
        }
    }

    fun submitCity(city: String) {
        closeCityDialog()
        loadWeather(city.trim())
    }

}

package com.example.weatercompose.ui

import com.example.weatercompose.data.model.Model

data class UiState(
    val isLoading: Boolean = false,
    val city: String = "Volgograd",
    val mainCard: Model? = null,
    val hourly: List<Model> = emptyList(),
    val daily: List<Model> = emptyList(),
    val isCityDialogVisible: Boolean = false,
    val error: String? = null

)
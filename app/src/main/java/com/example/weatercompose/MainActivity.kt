package com.example.weatercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.weatercompose.ui.CityInputDialog
import com.example.weatercompose.ui.WeatherViewModel
import com.example.weatercompose.ui.MainCard
import com.example.weatercompose.ui.TabLayout
import com.example.weatercompose.di.ViewModelFactory

const val API_KEY = "7e1df1c53f17499a825163433252511"

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val viewModel: WeatherViewModel = viewModel(
                factory = ViewModelFactory()
            )

            val state by viewModel.uiState.collectAsState()

            Image(
                painter = painterResource(R.drawable.bg),
                contentDescription = "bg",
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.8f),
                contentScale = ContentScale.Crop
            )

            if (state.isCityDialogVisible) {
                CityInputDialog(
                    currentCity = state.city,
                    onConfirm = { viewModel.submitCity(it) },
                    onDismiss = { viewModel.closeCityDialog() }
                )
            }

            Column {
                MainCard(
                    model = state.mainCard,
                    onRefresh = { viewModel.refresh() },
                    onCityClick = {
                        viewModel.openCityDialog()
                    }
                )

                TabLayout(
                    hour = state.hourly,
                    days = state.daily
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}


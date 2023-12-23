package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import com.example.weatherapp.operations.MY_API_KEY
import com.example.weatherapp.presentation.MyAppNavigator
import com.example.weatherapp.presentation.screens.WeatherViewModel
import com.example.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
 class MainActivity : ComponentActivity() {
    val viewModel :WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavigator(viewModel)
//                    viewModel.fetchWeatherData(MY_API_KEY,"london")
//                    viewModel.weatherData.observe(this, Observer {
//                        if (it!=null){
//                            Log.d("api test", "onCreate: data: $it")
//                        }
//                    })
                }
            }
        }
    }
}

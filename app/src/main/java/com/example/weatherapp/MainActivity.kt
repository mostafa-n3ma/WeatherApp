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
import com.example.weatherapp.presentation.MyAppNavigator
import com.example.weatherapp.presentation.screens.WeatherViewModel
import com.example.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.log

@ExperimentalMaterialApi
@AndroidEntryPoint
 class MainActivity : ComponentActivity() {
    val viewModel :WeatherViewModel by viewModels()
    companion object{
        val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavigator(viewModel)
                    viewModel.liveLocationList.observe(this, Observer {
                        Log.d(TAG, "onCreate:x99 liveLocationsData: ${it.size}")
                        if (it.isNullOrEmpty()){
                            viewModel.getDefaultDisplayLocation()
                        }
                    })

                    viewModel.liveMainDisplayLocation.observe(this, Observer {
                        if (it != null) {
                            Log.d(TAG, "onCreate:x99 live Main Display : $it + ${it.isLastSearchedLocation}")
                        }else{
                            Log.d(TAG, "onCreate:x99 live Main Display : null }")
                        }
                    })

                }
            }
        }
    }
}

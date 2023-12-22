package com.example.weatherapp.presentation.screens

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.presentation.components.HomeScreenFront




@ExperimentalMaterialApi
@Composable
fun HomeScreen(nav: NavHostController) {
    HomeScreenFront(nav)

//    val viewModel: WeatherViewModel = hiltViewModel()
//
//    // Fetch weather data when the screen is first composed
//    LaunchedEffect(Unit) {
//        viewModel.fetchWeatherData(apiKey = "YOUR_API_KEY", location = "London")
//    }
//
//    // Observe the weather data changes
//    val weatherData = viewModel.weatherData.observeAsState()
//    // You might want to use `weatherData.value` to access the data when it's available
//
//    // Log the API response
//    weatherData.value?.let { data ->
//        Log.d("API test", "Data = $data")
//    }
}




@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(nav = rememberNavController())
}
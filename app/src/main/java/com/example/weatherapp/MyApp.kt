package com.example.weatherapp

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.screens.CitiesWeatherScreen
import com.example.weatherapp.screens.HomeScreen

@ExperimentalMaterialApi
@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppDestinations.HomeScreen.name){
        composable(AppDestinations.HomeScreen.name){
            HomeScreen(navController)
        }
        composable(AppDestinations.SearchScreen.name){
            CitiesWeatherScreen(navController)
        }
    }

}




enum class AppDestinations{
    HomeScreen,
    SearchScreen
}
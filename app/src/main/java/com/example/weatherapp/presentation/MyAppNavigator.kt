package com.example.weatherapp.presentation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.presentation.screens.CitiesWeatherScreen
import com.example.weatherapp.presentation.screens.HomeScreen
import com.example.weatherapp.presentation.screens.WeatherViewModel

@ExperimentalMaterialApi
@Composable
fun MyAppNavigator(viewModel: WeatherViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppDestinations.HomeScreen.name){
        composable(
            AppDestinations.HomeScreen.name,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = {-it},
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = {-it},
                    animationSpec = tween(500)
                )
            }
        ){
            HomeScreen(navController,viewModel)
        }
        composable(
            AppDestinations.SearchScreen.name,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = {it},
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = {it},
                    animationSpec = tween(500)
                )
            }
        ){
            CitiesWeatherScreen(navController,viewModel)
        }

    }

}


enum class AppDestinations{
    HomeScreen,
    SearchScreen,
}
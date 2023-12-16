package com.example.weatherapp

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
            HomeScreen(navController)
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
            CitiesWeatherScreen(navController)
        }

    }

}


enum class AppDestinations{
    HomeScreen,
    SearchScreen,
}
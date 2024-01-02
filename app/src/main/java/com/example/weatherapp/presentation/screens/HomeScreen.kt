package com.example.weatherapp.presentation.screens

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.weatherapp.operations.MY_API_KEY
import com.example.weatherapp.operations.data_management.data_entities.NetWorkEntity
import com.example.weatherapp.presentation.components.HomeScreenFront




@ExperimentalMaterialApi
@Composable
fun HomeScreen(nav: NavHostController,viewModel: WeatherViewModel) {
    HomeScreenFront(nav,viewModel)
}




@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun HomePreview() {
//    HomeScreen(nav = rememberNavController())
}
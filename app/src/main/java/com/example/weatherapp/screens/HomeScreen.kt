package com.example.weatherapp.screens

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.components.HomeScreenFront

@ExperimentalMaterialApi
@Composable
fun HomeScreen(nav: NavHostController) {
    HomeScreenFront(nav)
}




@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(nav = rememberNavController())
}
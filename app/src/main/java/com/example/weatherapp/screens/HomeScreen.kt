package com.example.weatherapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.R
import com.example.weatherapp.components.BottomSheetScreen
import com.example.weatherapp.components.CityMainTextCompose
import com.example.weatherapp.components.HomeScreenBackgroundCompose
import com.example.weatherapp.components.HomeScreenFront
import com.example.weatherapp.ui.theme.DarkPrimary
import com.example.weatherapp.ui.theme.LightPrimary

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
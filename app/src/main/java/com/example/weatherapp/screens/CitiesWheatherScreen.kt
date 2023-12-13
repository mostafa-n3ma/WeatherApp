package com.example.weatherapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.DarkPrimary
import com.example.weatherapp.ui.theme.DarkSecondary
import com.example.weatherapp.ui.theme.DarkTertiary
import com.example.weatherapp.ui.theme.LinearGradient
import com.example.weatherapp.ui.theme.SearchBarGradient

val weatherItems = listOf<WeatherItem>(
    WeatherItem(19, 24, 18, "Montreal", "Canada", WeatherConditions.moon_cloud_mid_rain),
    WeatherItem(20, 21, -19, "Toronto", "Canada", WeatherConditions.moon_cloud_fast_wind),
    WeatherItem(13, 16, 8, "Tokyo", "Japan", WeatherConditions.sun_cloud_angled_rain),
    WeatherItem(23, 26, 16, "Tennessee", "United States", WeatherConditions.tornado),
    WeatherItem(17, 20, 12, "Baghdad", "Iraq", WeatherConditions.moon_cloud_mid_rain),
    WeatherItem(12, 19, 5, "Alkut", "Iraq", WeatherConditions.sun_cloud_mid_rain),
)
@Preview
@Composable
fun CitiesWeatherPreview() {
    CitiesWeatherScreen(rememberNavController())
}

@Composable
fun CitiesWeatherScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LinearGradient)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        WeatherBackButtonTopBar(navController)
        CitiesSearchBar(label = stringResource(id = R.string.search_for_a_city_or_airport))
        CitiesWeatherList(list = weatherItems)
    }
}


@Composable
fun CitiesSearchBar(label: String) {
    val editable = remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .background(SearchBarGradient, shape = RoundedCornerShape(22)) // Background color for visualization
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = DarkSecondary,
                modifier = Modifier.padding(start = 16.dp, end = 8.dp) // Adjust padding as needed
            )

            Text(
                text = label,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight(400),
                    color = DarkSecondary,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Start // Align label text to the center
                ),
                modifier = Modifier.weight(1f)
            )
        }

        BasicTextField(
            value = editable.value,
            onValueChange = { editable.value = it },
            modifier = Modifier.fillMaxWidth(),

        )
    }

}


@Composable
fun CitiesSearchBar99(label: String) {
    val editableTxt = remember {
        mutableStateOf("")
    }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(SearchBarGradient, shape = RoundedCornerShape(22)),
        horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        ){
            TextField(value = editableTxt.value,
                onValueChange = {editableTxt.value = it},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "search icon", tint = DarkSecondary)
                },
                label = { Text(text = label,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                        fontWeight = FontWeight(400),
                        color = DarkSecondary,
                        fontSize = 17.sp,
                        textAlign = TextAlign.Center
                    ) )},
                )
    }
}

@Composable
fun WeatherBackButtonTopBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            IconButton(onClick = {
                navController.popBackStack()
            },
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.back_btn),
                    contentDescription = "back btn",
                    tint = DarkTertiary,

                )
            }

            Text(
                text = "Weather",
                style = TextStyle(
                    fontFamily = FontFamily((Font(R.font.sf_pro_display_regular))),
                    fontWeight = FontWeight(400),
                    color = DarkPrimary,
                    fontSize = 28.sp
                )
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.more_btn),
            contentDescription = "more btn",
            tint = DarkPrimary,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(32.dp)
        )
    }
}


@Composable
fun CitiesWeatherList(list: List<WeatherItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(list) { item ->
            CityWeatherItem(item = item)
        }
    }
}

data class WeatherItem(
    val degree: Int,
    val h: Int,
    val l: Int,
    val city: String,
    val country: String,
    val condition: WeatherConditions
)

enum class WeatherConditions {
    tornado,
    sun_cloud_mid_rain,
    sun_cloud_angled_rain,
    moon_cloud_mid_rain,
    moon_cloud_fast_wind
}


@Composable
fun CityWeatherItem(item: WeatherItem) {
    val condition: String
    val conditionImage = when (item.condition) {
        WeatherConditions.tornado -> {
            condition = "Tornado"
            painterResource(id = R.drawable.big_tornado)
        }

        WeatherConditions.sun_cloud_mid_rain -> {
            condition = "Mid Rain"
            painterResource(id = R.drawable.big_sun_cloud_mid_rain)
        }

        WeatherConditions.sun_cloud_angled_rain -> {
            condition = "Showers"
            painterResource(id = R.drawable.big_sun_cloud_angled_rain)
        }

        WeatherConditions.moon_cloud_mid_rain -> {
            condition = "Mid Rain"
            painterResource(id = R.drawable.big_moon_cloud_mid_rain)
        }

        WeatherConditions.moon_cloud_fast_wind -> {
            condition = "Partly Cloudy"
            painterResource(id = R.drawable.big_moon_cloud_fast_wind)
        }
    }
    Box(
        modifier = Modifier
            .width(338.dp)
            .height(185.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.item_shap),
            contentDescription = "",
            Modifier.fillMaxSize()
        )
        Image(
            painter = conditionImage,
            contentDescription = "tornado",
            Modifier
                .size(160.dp)
                .align(Alignment.TopEnd)
                .padding(0.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 30.dp, start = 16.dp, bottom = 4.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "${item.degree}°",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight(400),
                    fontSize = 64.sp,
                    color = DarkPrimary
                )
            )
            Text(
                text = "H:${item.h}° L${item.l}°",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp,
                    color = DarkSecondary
                ),
            )
            Text(
                text = "${item.city},${item.country}",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight(400),
                    fontSize = 20.sp,
                    color = DarkPrimary
                ),
            )
        }

        Text(
            text = "${condition}",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                color = DarkSecondary
            ),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 18.dp, end = 20.dp)
        )


    }
}
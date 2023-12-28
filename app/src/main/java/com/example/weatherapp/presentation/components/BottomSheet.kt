package com.example.weatherapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.presentation.screens.WeatherViewModel
import com.example.weatherapp.presentation.ui.theme.GradiantBar
import com.example.weatherapp.presentation.ui.theme.DarkPrimary
import com.example.weatherapp.presentation.ui.theme.DarkSecondary
import com.example.weatherapp.presentation.ui.theme.DarkTertiary
import com.example.weatherapp.presentation.ui.theme.LightSecondary
import com.example.weatherapp.presentation.ui.theme.LinearGradient
import com.example.weatherapp.presentation.ui.theme.StatCardLinear
import com.example.weatherapp.presentation.ui.theme.color1

@ExperimentalMaterialApi
@Preview
@Composable
fun bottomPreview() {
//    BottomSheetScreen(viewModel)
}

val TAG ="BottomSheet"

@ExperimentalMaterialApi
@Composable
fun BottomSheetScreen(viewModel: WeatherViewModel):BottomSheetScaffoldState {

    val scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            SheetContent()
        },
        sheetPeekHeight = 325.dp,
        sheetShape = RoundedCornerShape(
            topStart = 44.dp,
            topEnd = 44.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
    ) {
        // Content for the screen
        HomeScreenBackgroundCompose(
            city = "Kut",
            degree = "44",
            condition = "Mostly Clear",
            H = "19",
            S = "12",
            bottomSheetScaffoldState = scaffoldState
        )

    }
        return scaffoldState
    }


@Composable
private fun SheetContent() {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(650.dp)
            .background(
                brush = LinearGradient,
                shape = RoundedCornerShape(
                    topStart = 44.dp,
                    topEnd = 44.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .verticalScroll(scrollState)

    ) {
        // Content for the bottom sheet
        // e.g., Text("Bottom Sheet Content")
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            BottomSheetTopBar()
            HourlyWeatherForeCast()
            AirQualityWidget()
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                UvIndexWidget()
                SunRiseWidget()
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                WindWidget()
                RainFallWidget()
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                FeelsLikeWidget()
                HumidityWidget()
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                VisibilityWidget()
                PressureWidget()
            }
        }
    }
}

@Composable
fun PressureWidget() {
    Column(
        modifier = Modifier
            .size(164.dp)
            .background(
                LinearGradient,
                shape = RoundedCornerShape(22)
            )
            .border(
                width = 2.dp,
                color1,
                shape = RoundedCornerShape(22)
            )

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, bottom = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "uv icon",
                tint = DarkTertiary,
                modifier = Modifier.size(15.dp)
            )
            Text(
                text = "PRESSURE",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight(600),
                    fontSize = 14.sp,
                    color = DarkTertiary
                )
            )
        }


        Image(
            painter = painterResource(id = R.drawable.pressure_round_bar),
            contentDescription = "pressure",
            modifier = Modifier
                .size(110.dp)
                .align(Alignment.CenterHorizontally)
        )

    }
}

@Composable
fun VisibilityWidget() {
    Column (
        modifier = Modifier
            .size(164.dp)
            .background(
                LinearGradient,
                shape = RoundedCornerShape(22)
            )
            .border(
                width = 2.dp,
                color = color1,
                shape = RoundedCornerShape(22)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.visibilty),
                contentDescription = "feels like ",
                Modifier.size(15.dp),
                tint = DarkTertiary
            )
            Text(
                text = "VISIBILTY",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                    fontWeight = FontWeight(600),
                    fontSize = 14.sp,
                    color = DarkTertiary
                )
            )
        }
        Text(
            text = "8 km",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                fontWeight = FontWeight(600),
                fontSize = 26.sp,
                color = DarkPrimary
            )
        )

        Text(
            text = "Similar to the day\nbefore",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                fontWeight = FontWeight(600),
                fontSize = 14.sp,
                color = DarkSecondary
            ),
            modifier = Modifier.padding(top = 30.dp)
        )

    }
}

@Composable
fun HumidityWidget() {
    Column (
        modifier = Modifier
            .size(164.dp)
            .background(
                LinearGradient,
                shape = RoundedCornerShape(22)
            )
            .border(
                width = 2.dp,
                color = color1,
                shape = RoundedCornerShape(22)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "feels like ",
                Modifier.size(15.dp),
                tint = DarkTertiary
            )
            Text(
                text = "HUMIDITY",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                    fontWeight = FontWeight(600),
                    fontSize = 14.sp,
                    color = DarkTertiary
                )
            )
        }
        Text(
            text = "90%",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                fontWeight = FontWeight(600),
                fontSize = 26.sp,
                color = DarkPrimary
            )
        )

        Text(
            text = "the dew point is 17\nright now",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                fontWeight = FontWeight(600),
                fontSize = 14.sp,
                color = DarkSecondary
            ),
            modifier = Modifier.padding(top = 30.dp)
        )

    }
}

@Composable
fun FeelsLikeWidget() {
    Column(
        modifier = Modifier
            .size(164.dp)
            .background(
                LinearGradient,
                shape = RoundedCornerShape(22)
            )
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(22),
                color = color1
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.feels_like),
                contentDescription = "feels like ",
                Modifier.size(15.dp),
                tint = DarkTertiary
            )
            Text(
                text = "FEELS LIKE",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                    fontWeight = FontWeight(600),
                    fontSize = 14.sp,
                    color = DarkTertiary
                )
            )
        }

        Text(
            text = "19°",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                fontWeight = FontWeight(600),
                fontSize = 26.sp,
                color = DarkPrimary
            )
        )

        Text(
            text = "similar to the actual\ntemperature",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                fontWeight = FontWeight(600),
                fontSize = 14.sp,
                color = DarkSecondary
            ),
            modifier = Modifier.padding(top = 30.dp)
        )


    }
}

@Composable
fun RainFallWidget() {
    Column(
        modifier = Modifier
            .size(164.dp)
            .background(
                LinearGradient,
                shape = RoundedCornerShape(22)
            )
            .border(
                width = 2.dp,
                color1,
                shape = RoundedCornerShape(22)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.rain_fall),
                contentDescription = "rain fall",
                tint = DarkTertiary,
                modifier = Modifier.size(15.dp)
            )
            Text(
                text = "RAINFALL",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                    fontWeight = FontWeight(600),
                    fontSize = 14.sp,
                    color = DarkTertiary
                )
            )

        }


        Text(
            text = "1.8 mm",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                fontWeight = FontWeight(600),
                fontSize = 26.sp,
                color = DarkPrimary
            )
        )

        Text(
            text = "in last hour",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                fontWeight = FontWeight(600),
                fontSize = 22.sp,
                color = DarkPrimary
            )
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = "1.2 mm expected in\n next 24h.",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                fontWeight = FontWeight(600),
                fontSize = 14.sp,
                color = DarkSecondary
            )
        )
    }
}


@Composable
fun WindWidget() {
    Column(
        modifier = Modifier
            .size(164.dp)
            .background(
                LinearGradient,
                shape = RoundedCornerShape(22)
            )
            .border(
                width = 2.dp,
                color1,
                shape = RoundedCornerShape(22)
            )

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp, start = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "uv icon",
                tint = DarkTertiary,
                modifier = Modifier.size(15.dp)
            )
            Text(
                text = "WIND",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight(600),
                    fontSize = 14.sp,
                    color = DarkTertiary
                )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.campos),
                contentDescription = "wid",
                modifier = Modifier
                    .size(200.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = "9.7",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                        fontWeight = FontWeight(600),
                        color = DarkPrimary,
                        fontSize = 24.sp
                    ),

                    )

                Text(
                    text = "km/h",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                        fontWeight = FontWeight(400),
                        color = DarkPrimary,
                        fontSize = 18.sp
                    ),

                    )
            }
        }

    }
}

@Composable
fun UvIndexWidget() {
    Column(
        modifier = Modifier
            .width(164.dp)
            .height(164.dp)
            .background(
                LinearGradient,
                shape = RoundedCornerShape(22)
            )
            .border(
                width = 1.dp,
                color1,
                shape = RoundedCornerShape(22)
            )
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.uv),
                contentDescription = "uv icon",
                tint = DarkTertiary,
                modifier = Modifier.size(15.dp)
            )
            Text(
                text = "UV INDEX",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight(600),
                    fontSize = 14.sp,
                    color = DarkTertiary
                )
            )
        }
        Text(
            text = "4",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                fontWeight = FontWeight(700),
                fontSize = 28.sp,
                color = DarkPrimary,
            )
        )

        Text(
            text = "Moderate",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                fontWeight = FontWeight(700),
                fontSize = 24.sp,
                color = DarkPrimary,
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth()
                .background(
                    GradiantBar,
                    shape = RoundedCornerShape(50)
                )
        )


    }
}

@Composable
fun SunRiseWidget() {
    Column(
        modifier = Modifier
            .width(164.dp)
            .height(164.dp)
            .background(
                LinearGradient,
                shape = RoundedCornerShape(22)
            )
            .border(
                width = 1.dp,
                color1,
                shape = RoundedCornerShape(22)
            )
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sun_rise),
                contentDescription = "sun rise",
                tint = DarkTertiary,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "SUNRISE",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight(600),
                    fontSize = 15.sp,
                    color = DarkTertiary
                )
            )
        }
        Text(
            text = "5:28 AM",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                fontWeight = FontWeight(400),
                fontSize = 32.sp,
                color = DarkPrimary
            )
        )
        Image(
            painter = painterResource(id = R.drawable.sun_rise_curve),
            contentDescription = "sun rise curve",
            Modifier.height(60.dp)
        )
        Text(
            text = "Sunset: 7:25PM",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                fontWeight = FontWeight(400),
                fontSize = 12.sp,
                color = DarkSecondary
            )

        )
    }
}


@Composable
fun BottomSheetTopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LinearGradient),
    ) {
        val underLinePosition = remember {
            mutableStateOf(true)
        }
        Image(
            modifier = Modifier
                .padding(top = 8.dp)
                .width(48.dp)
                .height(5.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.indicator),
            contentDescription = "indicator"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp, bottom = 4.dp, start = 32.dp, end = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(100.dp, Alignment.CenterHorizontally)
        ) {
            Text(
                modifier = Modifier
                    .width(111.dp)
                    .height(20.dp)
                    .clickable {
                        underLinePosition.value = true
                    },
                text = "Hourly Forecast",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontSize = 15.sp,
                    fontWeight = FontWeight(600),
                    color = DarkSecondary
                )
            )
            Text(
                modifier = Modifier
                    .width(111.dp)
                    .height(20.dp)
                    .clickable {
                        underLinePosition.value = false
                    },
                text = "Weekly Forecast",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontSize = 15.sp,
                    fontWeight = FontWeight(600),
                    color = DarkSecondary
                ),
            )
        }
        Spacer(
            modifier = Modifier
                .padding(start = 30.dp, end = 35.dp)
                .width(120.dp)
                .align(
                    if (underLinePosition.value) {
                        Start
                    } else {
                        End
                    }
                )
                .height(1.dp)
                .background(StatCardLinear)
        )


    }
}


@Composable
fun HourlyWeatherForeCast() {
    val state = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 20.dp)
            .horizontalScroll(state),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
    ) {
        repeat(24) {
            HourStateCheckedComposable()
        }
    }
}

@Composable
fun AirQualityWidget() {
    Column(
        Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .width(348.dp)
                .height(168.dp)
                .padding(top = 32.dp, bottom = 8.dp)
                .background(
                    LinearGradient,
                    shape = RoundedCornerShape(22.dp)
                )
                .border(
                    width = 1.dp,
                    color = color1,
                    shape = RoundedCornerShape(22.dp)
                )
                .align(Alignment.CenterHorizontally)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.air_quialty),
                    contentDescription = "air quality",
                    Modifier.size(18.dp),
                    tint = DarkTertiary
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = "AIR QUALITY",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                        fontWeight = FontWeight(600),
                        fontSize = 14.sp,
                        color = DarkTertiary
                    )
                )
            }


            Text(
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 4.dp),
                text = "3-Low Health Risk",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                    fontWeight = FontWeight(600),
                    fontSize = 20.sp,
                    color = DarkPrimary
                )
            )


            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .padding(start = 16.dp, top = 8.dp, bottom = 4.dp, end = 16.dp)
                    .background(GradiantBar, shape = RoundedCornerShape(50))
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.5.dp)
                    .padding(start = 16.dp, top = 8.dp, bottom = 4.dp, end = 16.dp)
                    .background(LightSecondary, shape = RoundedCornerShape(50))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "See more",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                        color = DarkPrimary,
                        fontSize = 16.sp
                    )
                )

                Icon(
                    painter = painterResource(id = R.drawable.see_more),
                    contentDescription = "see more",
                    tint = DarkTertiary,
                    modifier = Modifier.size(16.dp)
                )

            }


        }
    }
}

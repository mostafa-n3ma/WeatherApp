package com.example.weatherapp.components

import android.hardware.camera2.params.BlackLevelPattern
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.darkColors
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
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
import com.example.weatherapp.AppDestinations
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.DarkPrimary
import com.example.weatherapp.ui.theme.DarkSecondary
import com.example.weatherapp.ui.theme.LinearGradient
import com.example.weatherapp.ui.theme.StatCardLinear
import com.example.weatherapp.ui.theme.TransparentColor


@Composable
fun HomeScreenBackgroundCompose(city: String, degree: String, condition: String, H: String, S: String) {
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.sky)
            , contentDescription = stringResource(R.string.sky_background),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize())
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CityMainTextCompose(city,degree, condition, H, S)
            Image(modifier = Modifier
                .width(390.dp)
                .height(390.dp),
                painter = painterResource(id = R.drawable.home),
                contentDescription = "house img"
            )
        }
    }

}


@Composable
fun CityMainTextCompose(city: String, degree: String, condition: String, H: String, S: String) {
    Column(modifier = Modifier
        .width(390.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(modifier = Modifier
            .fillMaxWidth(),
            text = city,
            style = TextStyle(
                fontSize = 34.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                fontWeight = FontWeight(400),
                color = DarkPrimary,
                textAlign = TextAlign.Center,
                letterSpacing = 0.37.sp,
            )
        )

        Text(modifier = Modifier
            .fillMaxWidth(),
            text = "${degree}°",
            style = TextStyle(
                fontSize = 96.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                fontWeight = FontWeight(200),
                color = DarkPrimary,
                textAlign = TextAlign.Center,
            )
        )


        Text(modifier = Modifier.padding(bottom = 0.dp),
            text = condition,
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                fontWeight = FontWeight(600),
                color = DarkSecondary,
                textAlign = TextAlign.Center,
                letterSpacing = 0.38.sp,
            )
        )
        Text(modifier = Modifier.padding(top = 0.dp),
            text = "H:${H}°   L:${S}°",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                fontWeight = FontWeight(600),
                color = DarkPrimary,
                textAlign = TextAlign.Center,
                letterSpacing = 0.38.sp,
            )
        )

    }
}


@ExperimentalMaterialApi
@Composable
fun HomeScreenFront(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        BottomSheetScreen()

        // Elevated Image on top of BottomSheetScreen
        Box(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)){
            Image(
                modifier = Modifier
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.height) {
                            placeable.placeRelative(0, (1).dp.roundToPx()) // Example elevation
                        }
                    }
                    .fillMaxWidth()
                    .size(height = 100.dp, width = 2000.dp)
                    .align(Alignment.BottomCenter)
                    .background(TransparentColor),
                painter = painterResource(id = R.drawable.rectangle),
                contentDescription = "rectangular"
            )
            Image(
                modifier = Modifier
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.height) {
                            placeable.placeRelative(0, (1).dp.roundToPx()) // Example elevation
                        }
                    }
                    .fillMaxWidth()
                    .size(height = 100.dp, width = 2000.dp)
                    .align(Alignment.BottomCenter)
                    .background(TransparentColor),
                painter = painterResource(id = R.drawable.strock),
                contentDescription = "rectangular"
            )

            IconButton(
                modifier = Modifier
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.height) {
                            placeable.placeRelative(0, (1).dp.roundToPx()) // Example elevation
                        }
                    }
                    .align(Alignment.BottomCenter),
                onClick = {  }) {
                Box(modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 24.dp)){
                    Image(painter = painterResource(id = R.drawable.add_btn), contentDescription ="",Modifier.size(64.dp) )
                }
            }


            Row(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(248.dp, Alignment.CenterHorizontally),
            ) {
                // Child views.
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.location_btn), contentDescription = stringResource(
                        R.string.location_button
                    ),
                        Modifier.size(44.dp),
                        tint = DarkPrimary
                    )
                }
                IconButton(onClick = {
                    navController.navigate(AppDestinations.SearchScreen.name)
                }) {
                    Icon(painter = painterResource(id = R.drawable.list_btn), contentDescription = stringResource(
                        R.string.location_button
                    ),
                        Modifier.size(44.dp),
                        tint = DarkPrimary
                    )
                }

            }



        }

    }
}


@Composable
fun HourStateComposable(hour:String, degree: String) {
    val dot = "°"
    Box(
        modifier = Modifier
            .width(60.dp)
            .height(146.dp)
            .background(
                brush = StatCardLinear,
                shape = RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 50.dp,
                    bottomStart = 50.dp,
                    bottomEnd = 50.dp
                )

            ),
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 8.dp, end = 8.dp)
        ){
            Text(modifier = Modifier
                .width(43.dp)
                .height(20.dp),
                text = hour,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight(600),
                    color = DarkPrimary,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image( modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.big_moon_cloud_mid_rain),
                    contentDescription = "small_moon_cloud_mid_rain",

                    )
                Text(modifier = Modifier
                    .width(30.dp)
                    .height(18.dp),
                    text = "30%",
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF40CBD8),

                        textAlign = TextAlign.Center,
                    )
                )
            }


            Spacer(modifier = Modifier.height(8.dp))




            Text(modifier = Modifier
                .fillMaxWidth()
                .height(24.dp),
                text = "${degree}${dot}",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight(400),
                    color = DarkPrimary,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp,
                    letterSpacing = 0.38.sp,
                )
            )
        }
    }
}


@Composable
fun HourStateCheckedComposable() {
    Column(modifier = Modifier
        .shadow(elevation = 10.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
        .border(width = 1.dp, color = Color(0x33FFFFFF), shape = RoundedCornerShape(36.dp) )
        .width(60.dp)
        .height(146.dp)
        .background(color = Color(0x3348319D), shape = RoundedCornerShape(size = 30.dp))
        .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Child views.
        Text(modifier = Modifier
            .width(43.dp)
            .height(20.dp),
            text = "12 AM",

            // Default/Bold/Subheadline
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                fontWeight = FontWeight(600),
                color = Color(0xFFFFFFFF),
            )
        )

        Column(modifier = Modifier
            .width(32.dp)
            .height(50.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp,Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Child views.
            Image(modifier =Modifier
                .padding(0.dp)
                .width(32.dp)
                .height(32.dp),
                painter = painterResource(id = R.drawable.big_moon_cloud_mid_rain),
                contentDescription = "image description",
                contentScale = ContentScale.Crop
            )


            Text(modifier = Modifier
                .width(30.dp)
                .height(18.dp),
                text = "30%",
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF40CBD8),
                    textAlign = TextAlign.Center,
                )
            )

        }


        Text(modifier = Modifier
            .width(30.dp)
            .height(24.dp),
            text = "19°",

            // Default/Regular/Title3
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 24.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
                letterSpacing = 0.38.sp,
            )
        )






    }
}



@ExperimentalMaterialApi
@Preview(showBackground = false)
@Composable
fun MainTxtPreview() {
  HourStateComposable(hour = "12 AM", degree = "19")
}
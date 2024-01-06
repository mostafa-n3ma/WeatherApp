@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.weatherapp.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.operations.data_management.data_entities.DomainEntity
import com.example.weatherapp.presentation.AppDestinations
import com.example.weatherapp.presentation.ui.theme.DarkPrimary
import com.example.weatherapp.presentation.ui.theme.DarkSecondary
import com.example.weatherapp.presentation.ui.theme.DarkTertiary
import com.example.weatherapp.presentation.ui.theme.LinearGradient
import com.example.weatherapp.presentation.ui.theme.TransparentColor

val TAG = "CitiesWeatherScreen"

@Preview
@Composable
fun CitiesWeatherPreview() {
//    CitiesWeatherScreen(rememberNavController(), viewModel)
}

@Composable
fun CitiesWeatherScreen(navController: NavController, viewModel: WeatherViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LinearGradient)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        WeatherBackButtonTopBar(navController)
        SearchBar(viewModel)
        CitiesWeatherList(viewModel,navController)

    }
}

@Composable
fun HideKeyBoard() {
    LocalSoftwareKeyboardController.current?.hide()
}

@ExperimentalComposeUiApi
@Composable
fun SearchBar(viewModel: WeatherViewModel) {
    val editableText: MutableState<String> = remember {
        mutableStateOf("")
    }

    val focusState: State<Boolean?> = viewModel.focusOnTextField.observeAsState()
    val focusRequester = FocusRequester()
    LaunchedEffect(focusState.value){
        if (focusState.value == true){
            focusRequester.requestFocus()
            viewModel.requestFocusOnTextField(false)
        }
    }

    OutlinedTextField(
        value = editableText.value,
        onValueChange = { editableText.value = it },
        modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
        singleLine = true,
        label = { Text(text = stringResource(id = R.string.search_for_a_city_or_airport)) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = DarkSecondary,
            focusedLabelColor = DarkSecondary,
            cursorColor = DarkSecondary,
            backgroundColor = TransparentColor,
            textColor = DarkPrimary,
            unfocusedLabelColor = DarkSecondary,
            unfocusedBorderColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = DarkSecondary,
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                Log.d("search Bar testing", "SearchBar: search(${editableText.value}")
                if (editableText.value.trim().isNotEmpty()){
                    viewModel.search(editableText.value)
                    viewModel.hideKeBoard()
                }
            }
        ),
        trailingIcon = {
            IconButton(onClick = { editableText.value = "" }) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "clear text",
                    tint = when (editableText.value) {
                        "" -> {
                            Color.Transparent
                        }

                        else -> {
                            DarkSecondary
                        }
                    }
                )
            }
        },
    )


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
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp)
            ) {
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
fun CitiesWeatherList(viewModel: WeatherViewModel, navController: NavController) {
    val cities: State<List<DomainEntity>?> = viewModel.liveLocationList.observeAsState()
    Log.d(TAG, "CitiesWeatherList: cities:$cities")
    when(cities.value){
        null->{}
        else->{
            viewModel.getLocationAfterObservation(cities.value!!)
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (cities.value != null){
            items(cities.value!!.reversed()
            ){
                CityWeatherItem(it,viewModel,navController)
            }
        }

    }
}

@Composable
fun CityWeatherItem(item: DomainEntity, viewModel: WeatherViewModel, navController: NavController) {

    Box(
        modifier = Modifier
            .width(338.dp)
            .height(185.dp)
            .clickable {
                viewModel.setToMainDisplay(item)
                navController.popBackStack()
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.item_shap),
            contentDescription = "",
            Modifier.fillMaxSize()
        )
        AsyncImage(
            model = "https:${item.condition_ic}",
            contentDescription = "ic",
            Modifier
                .size(140.dp)
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
                text = "${item.tempC}°",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight(400),
                    fontSize = 64.sp,
                    color = DarkPrimary
                )
            )
            Text(
                text = "H:${item.maxtempC}° L${item.mintempC}°",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp,
                    color = DarkSecondary
                ),
            )
            Text(
                text = "${item.name},${item.country}",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight(400),
                    fontSize = 20.sp,
                    color = DarkPrimary
                ),
            )
        }

        Text(
            text = "${item.condition_txt}",
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
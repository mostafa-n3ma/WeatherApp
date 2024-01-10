package com.example.weatherapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import com.example.weatherapp.presentation.MyAppNavigator
import com.example.weatherapp.presentation.screens.WeatherViewModel
import com.example.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
 class MainActivity : ComponentActivity() {
    val viewModel :WeatherViewModel by viewModels()
    companion object{
        val TAG = "MainActivity"
        private const val REQUEST_LOCATION_PERMISSION = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavigator(viewModel)
                    subscribeObservers()

                }
            }
        }
    }










    private fun subscribeObservers() {
        var updated =false
        viewModel.liveLocationList.observe(this, Observer {
            Log.d(TAG, "onCreate:x99 liveLocationsData: ${it}")
            if (it.isNullOrEmpty()){
                viewModel.getDefaultDisplayLocation()
            }else{
                Log.d(TAG, "onCreate: calling update cache once: update:$updated")
                if (!updated){
                    viewModel.updateCacheData(it)
                    updated =true
                    viewModel.getLocationAfterObservation(it)
                }
            }
        })
        viewModel.liveMainDisplayLocation.observe(this, Observer {
            Log.d(TAG, "onCreate: Main Display Location : $it")
        })

        viewModel.hideKeyBoard.observe(this, Observer {
            if (it){
                hideKeyboard()
            }
        })
    }




}



fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
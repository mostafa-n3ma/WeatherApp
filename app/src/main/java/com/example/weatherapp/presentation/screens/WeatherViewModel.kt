package com.example.weatherapp.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.operations.data_management.data_entities.NetWorkEntity
import com.example.weatherapp.operations.data_management.data_sources.RemoteDataSource.WeatherApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
@Inject
constructor(
    private val retrofitService:WeatherApiService
):ViewModel(){
    private val _weatherData = MutableLiveData<NetWorkEntity?>()
    val weatherData: MutableLiveData<NetWorkEntity?> get() = _weatherData

    init {
        _weatherData.value = null
    }

    fun fetchWeatherData(apiKey:String,location:String){
        viewModelScope.launch{
            val response = retrofitService.getWeatherForecast(apiKey, location)
            _weatherData.value = response
        }
    }

}
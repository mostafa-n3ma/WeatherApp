package com.example.weatherapp.operations.data_management.data_sources.RemoteDataSource

import com.example.weatherapp.operations.data_management.data_entities.NetWorkEntity

interface DefaultRemoteDataSource {

    suspend fun getWeatherForecast(key:String,location:String):NetWorkEntity
}
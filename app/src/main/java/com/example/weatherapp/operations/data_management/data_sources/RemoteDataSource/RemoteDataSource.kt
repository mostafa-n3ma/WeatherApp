package com.example.weatherapp.operations.data_management.data_sources.RemoteDataSource

import com.example.weatherapp.operations.data_management.data_entities.NetWorkEntity
import javax.inject.Inject

class RemoteDataSource
@Inject
constructor(private val weatherApiService: WeatherApiService) :DefaultRemoteDataSource{
    override suspend fun getWeatherForecast(key: String, location: String): NetWorkEntity {
       return weatherApiService.getWeatherForecast(key,location)
    }
}
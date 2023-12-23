package com.example.weatherapp.operations.data_management.data_sources.RemoteDataSource

import com.example.weatherapp.operations.data_management.data_entities.NetWorkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") numDays: Int=7,
        @Query("aqi") includeAQI: String="no",
        @Query("alerts") includeAlerts: String="no"
    ): NetWorkEntity
}
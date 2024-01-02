package com.example.weatherapp.operations.data_management.data_entities

data class DomainEntity(
    var id: Int?=null ,
    var isLastSearchedLocation:Boolean = false,



    val name: String? = null,
    val country: String? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val lastUpdated: String?= null,
    val tempC: Double? = null,
    val feelslikeC: Double?,
    val condition_txt: String?,
    val condition_ic: String?,
    val airQuality: Int?,
    val uv: Int?,
    val windKph: Double?,
    val windDir: String?,
    val humidity: Int?,
    val visKm: Int?,
    val pressureIn: Double?,
    val maxtempC: Double?,
    val mintempC: Double?,
    val dailyChanceOfRain: Int?,
    val sunrise: String?,
    val sunset: String?,
    val hourly_forecast:List<HourlyForecast>,
    val daily_forecast:List<DailyForecast>
)

package com.example.weatherapp.operations.data_management.data_entities.NetWorkEntitySubClasses

import com.google.gson.annotations.SerializedName

data class Forecast (

    @SerializedName("forecastday" ) var forecastday : ArrayList<Forecastday> = arrayListOf()

)

data class Forecastday (
    @SerializedName("date"       ) var date      : String?         = null,//
    @SerializedName("day"        ) var day       : Day?            = Day(),//
    @SerializedName("astro"      ) var astro     : Astro?          = Astro(),//
    @SerializedName("hour"       ) var hour      : ArrayList<Hour> = arrayListOf()//
)

data class Hour (
    @SerializedName("time"           ) var time         : String?     = null,//
    @SerializedName("temp_c"         ) var tempC        : Double?     = null,//
    @SerializedName("is_day"         ) var isDay        : Int?        = null,
    @SerializedName("condition"      ) var condition    : Condition?  = Condition(),//
    @SerializedName("cloud"          ) var cloud        : Int?        = null,
    @SerializedName("will_it_rain"   ) var willItRain   : Int?        = null,
    @SerializedName("chance_of_rain" ) var chanceOfRain : Int?        = null,
    @SerializedName("vis_km"         ) var visKm        : Int?        = null,
    @SerializedName("uv"             ) var uv           : Int?        = null,
    @SerializedName("air_quality"    ) var airQuality   : AirQuality? = AirQuality()

)

data class Astro (
    @SerializedName("sunrise"           ) var sunrise          : String? = null,//
    @SerializedName("sunset"            ) var sunset           : String? = null,//
    @SerializedName("is_sun_up"         ) var isSunUp          : Int?    = null
)

data class Day (
    @SerializedName("maxtemp_c"            ) var maxtempC          : Double?     = null,//
    @SerializedName("mintemp_c"            ) var mintempC          : Double?     = null,//
    @SerializedName("avgtemp_c"            ) var avgtempC          : Double?     = null,//
    @SerializedName("avgvis_km"            ) var avgvisKm          : Int?        = null,
    @SerializedName("avghumidity"          ) var avghumidity       : Int?        = null,
    @SerializedName("daily_will_it_rain"   ) var dailyWillItRain   : Int?        = null,
    @SerializedName("daily_chance_of_rain" ) var dailyChanceOfRain : Int?        = null,//
    @SerializedName("condition"            ) var condition         : Condition?  = Condition(),//
    @SerializedName("air_quality"          ) var airQuality        : AirQuality? = AirQuality()

)
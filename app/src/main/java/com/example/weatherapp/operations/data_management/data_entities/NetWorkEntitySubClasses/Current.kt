package com.example.weatherapp.operations.data_management.data_entities.NetWorkEntitySubClasses

import com.google.gson.annotations.SerializedName

data class Current (
    @SerializedName("last_updated"       ) var lastUpdated      : String?     = null,//
    @SerializedName("temp_c"             ) var tempC            : Double?        = null,//
    @SerializedName("is_day"             ) var isDay            : Int?        = null,
    @SerializedName("condition"          ) var condition        : Condition?  = Condition(),//
    @SerializedName("wind_kph"           ) var windKph          : Double?     = null,//
    @SerializedName("wind_dir"           ) var windDir          : String?     = null,//
    @SerializedName("pressure_mb"        ) var pressureMb       : Int?        = null,
    @SerializedName("pressure_in"        ) var pressureIn       : Double?     = null,//
    @SerializedName("precip_mm"          ) var precipMm         : Int?        = null,
    @SerializedName("precip_in"          ) var precipIn         : Int?        = null,
    @SerializedName("humidity"           ) var humidity         : Int?        = null,//
    @SerializedName("cloud"              ) var cloud            : Int?        = null,
    @SerializedName("feelslike_c"        ) var feelslikeC       : Double?     = null,//
    @SerializedName("vis_km"             ) var visKm            : Int?        = null,//
    @SerializedName("uv"                 ) var uv               : Int?        = null,//
    @SerializedName("gust_mph"           ) var gustMph          : Double?     = null,
    @SerializedName("gust_kph"           ) var gustKph          : Double?     = null,
    @SerializedName("air_quality"        ) var airQuality       : AirQuality? = AirQuality()//
)
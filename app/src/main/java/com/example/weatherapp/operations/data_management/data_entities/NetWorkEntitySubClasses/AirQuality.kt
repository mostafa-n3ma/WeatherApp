package com.example.weatherapp.operations.data_management.data_entities.NetWorkEntitySubClasses

import com.google.gson.annotations.SerializedName

data class AirQuality (

    @SerializedName("co"             ) var co             : Int?    = null,
    @SerializedName("no2"            ) var no2            : Double? = null,
    @SerializedName("o3"             ) var o3             : Double? = null,
    @SerializedName("so2"            ) var so2            : Double? = null,
    @SerializedName("pm2_5"          ) var pm25           : Double? = null,
    @SerializedName("pm10"           ) var pm10           : Double? = null,
    @SerializedName("us-epa-index"   ) var us_epa_index   : Int?    = null,//
    @SerializedName("gb-defra-index" ) var gb_defra_index : Int?    = null

)
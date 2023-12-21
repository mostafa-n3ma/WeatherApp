package com.example.weatherapp.operations.data_management.data_entities.NetWorkEntitySubClasses

import com.google.gson.annotations.SerializedName

data class Location (
    @SerializedName("name"            ) var name           : String? = null,//
    @SerializedName("region"          ) var region         : String? = null,
    @SerializedName("country"         ) var country        : String? = null,//
    @SerializedName("lat"             ) var lat            : Double? = null,//
    @SerializedName("lon"             ) var lon            : Double? = null,//
)
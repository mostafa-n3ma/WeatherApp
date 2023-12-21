package com.example.weatherapp.operations.data_management.data_entities

import com.example.weatherapp.operations.data_management.data_entities.NetWorkEntitySubClasses.Current
import com.example.weatherapp.operations.data_management.data_entities.NetWorkEntitySubClasses.Forecast
import com.example.weatherapp.operations.data_management.data_entities.NetWorkEntitySubClasses.Location
import com.google.gson.annotations.SerializedName

data class NetWorkEntity (
    @SerializedName("location" ) var location : Location? = Location(),
    @SerializedName("current"  ) var current  : Current?  = Current(),
    @SerializedName("forecast" ) var forecast : Forecast? = Forecast()
)








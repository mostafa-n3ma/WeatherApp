package com.example.weatherapp.operations.data_management.data_utils
import androidx.room.TypeConverter
import com.example.weatherapp.operations.data_management.data_entities.HourlyForecast
import com.example.weatherapp.operations.data_management.data_entities.DailyForecast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromHourlyForecastList(value: List<HourlyForecast>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toHourlyForecastList(value: String): List<HourlyForecast>? {
        val listType = object : TypeToken<List<HourlyForecast>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromDailyForecastList(value: List<DailyForecast>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toDailyForecastList(value: String): List<DailyForecast>? {
        val listType = object : TypeToken<List<DailyForecast>>() {}.type
        return gson.fromJson(value, listType)
    }
}

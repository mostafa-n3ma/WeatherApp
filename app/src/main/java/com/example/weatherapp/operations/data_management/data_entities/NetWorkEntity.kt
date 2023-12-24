package com.example.weatherapp.operations.data_management.data_entities

import com.example.weatherapp.operations.data_management.data_entities.DailyForecast.Companion.mapToDailyForecastList
import com.example.weatherapp.operations.data_management.data_entities.HourlyForecast.Companion.mapToHourlyForecastList
import com.example.weatherapp.operations.data_management.data_entities.NetWorkEntitySubClasses.Current
import com.example.weatherapp.operations.data_management.data_entities.NetWorkEntitySubClasses.Forecast
import com.example.weatherapp.operations.data_management.data_entities.NetWorkEntitySubClasses.Location
import com.example.weatherapp.operations.data_management.data_utils.DefaultMapper
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class NetWorkEntity (
    @SerializedName("location" ) var location : Location? = Location(),
    @SerializedName("current"  ) var current  : Current?  = Current(),
    @SerializedName("forecast" ) var forecast : Forecast? = Forecast()
)




class NetWorkMapper
@Inject
constructor():DefaultMapper<DomainEntity,NetWorkEntity>{
    override fun mapToDomain(entity: NetWorkEntity): DomainEntity {
        return DomainEntity(
            name = entity.location!!.name,
            country = entity.location!!.country,
            lat = entity.location!!.lat,
            lon = entity.location!!.lon,


            lastUpdated = entity.current!!.lastUpdated,
            tempC = entity.current!!.tempC,
            feelslikeC = entity.current!!.feelslikeC,
            condition_txt = entity.current!!.condition!!.text,
            condition_ic = entity.current!!.condition!!.icon,
            airQuality = entity.current!!.airQuality!!.us_epa_index,
            uv = entity.current!!.uv,
            windKph = entity.current!!.windKph,
            windDir = entity.current!!.windDir,
            humidity = entity.current!!.humidity,
            visKm = entity.current!!.visKm,
            pressureIn = entity.current!!.pressureIn,
            maxtempC = entity.forecast!!.forecastday[0].day!!.maxtempC,
            mintempC = entity.forecast!!.forecastday[0].day!!.mintempC,
            dailyChanceOfRain = entity.forecast!!.forecastday[0].day!!.dailyChanceOfRain,
            sunrise = entity.forecast!!.forecastday[0].astro!!.sunrise,
            sunset = entity.forecast!!.forecastday[0].astro!!.sunset,
            hourly_forecast = mapToHourlyForecastList(entity.forecast!!.forecastday[0].hour),
            daily_forecast = mapToDailyForecastList(entity.forecast!!.forecastday)
        )
    }

    override fun mapFromDomain(domain: DomainEntity): NetWorkEntity {
            return NetWorkEntity()
    }

    override fun mapEntitiesList(list: List<NetWorkEntity>): List<DomainEntity> {
        return listOf()
    }

}



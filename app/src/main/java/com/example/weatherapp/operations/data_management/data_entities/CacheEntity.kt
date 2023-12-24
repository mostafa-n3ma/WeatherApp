package com.example.weatherapp.operations.data_management.data_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.operations.data_management.data_entities.NetWorkEntitySubClasses.Forecastday
import com.example.weatherapp.operations.data_management.data_entities.NetWorkEntitySubClasses.Hour
import com.example.weatherapp.operations.data_management.data_utils.DefaultMapper
import javax.inject.Inject

@Entity(tableName = "Cache Entities")
data class CacheEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    var isLastSearchedLocation:Boolean = false,
    // Location.
    val name: String? = null,
    val country: String? = null,
    val lat: Double? = null,
    val lon: Double? = null,

    // current.
    val lastUpdated: String? = null,
    val tempC: Int? = null,
    val feelslikeC: Double? = null,
    val condition_txt: String? = null,
    val condition_ic: String? = null,
    val airQuality: Int? = null,
    val uv: Int? = null,
    val windKph: Double? = null,
    val windDir: String? = null,
    val humidity: Int? = null,
    val visKm: Int? = null,
    val pressureIn: Double? = null,

    // forcaste.Forecastday.day
    val maxtempC: Double? = null,
    val mintempC: Double? = null,
    val dailyChanceOfRain: Int? = null,

    // forcaste.Forecastday.astro
    val sunrise: String? = null,
    val sunset: String? = null,

    val hourly_forecast:List<HourlyForecast>,
    val daily_forecast:List<DailyForecast>
    )

data class DailyForecast(
    val date:String?= null,// forecast.forecastday[i].day.date
    val day_name : String?= null,  // create fun to the name of that day (sunday,monday,....)
    val temp_c : Double?= null, // forecast.forecastday[i].day.avgtemp_c
    val condition_txt: String?= null,//forecast.forecastday[i].day.condition.text
    val condition_ic: String?= null,//forecast.forecastday[i].day.condition.icon
){
    companion object{
        fun mapToDailyForecast(forecastday: Forecastday):DailyForecast{
            return DailyForecast(
                date = forecastday.date,
                day_name = forecastday.date,
                temp_c = forecastday.day!!.avgtempC,
                condition_txt = forecastday.day!!.condition!!.text,
                condition_ic = forecastday.day!!.condition!!.icon
            )
        }

        fun mapToDailyForecastList(forecastDayArray:ArrayList<Forecastday>):List<DailyForecast>{
            return forecastDayArray.map { mapToDailyForecast(it) }
        }

    }
}

data class HourlyForecast(
    val time:String?= null,//forecast.forecastday[i].hour.time
    val temp_c:Double?=null,//forecast.forecastday[i].hour.temp_c
    val condition_txt: String?=null,//forecast.forecastday[i].hour.conition.text
    val condition_ic: String?=null//forecast.forecastday[i].hour.condition.icon
){
    companion object{
        fun mapToHourlyForecast(hour: Hour):HourlyForecast{
            return HourlyForecast(
                time = hour.time,
                temp_c = hour.tempC,
                condition_txt = hour.condition!!.text,
                condition_ic = hour.condition!!.icon
            )
        }
        fun mapToHourlyForecastList(hoursList: ArrayList<Hour>):List<HourlyForecast>{
            return hoursList.map { mapToHourlyForecast(it) }
        }

    }
}


class CacheMapper
@Inject
constructor():DefaultMapper<DomainEntity,CacheEntity>{
    override fun mapToDomain(entity: CacheEntity): DomainEntity {
        return DomainEntity(
            id = entity.id,
            isLastSearchedLocation = entity.isLastSearchedLocation,
            name = entity.name,
            country = entity.country,
            lat = entity.lat,
            lon = entity.lon,
            lastUpdated = entity.lastUpdated,
            tempC = entity.tempC,
            feelslikeC = entity.feelslikeC,
            condition_txt = entity.condition_txt,
            condition_ic = entity.condition_ic,
            airQuality = entity.airQuality,
            uv = entity.uv,
            windKph = entity.windKph,
            windDir = entity.windDir,
            humidity = entity.humidity,
            visKm = entity.visKm,
            pressureIn = entity.pressureIn,
            maxtempC = entity.maxtempC,
            mintempC = entity.mintempC,
            dailyChanceOfRain = entity.dailyChanceOfRain,
            sunrise = entity.sunrise,
            sunset = entity.sunset,
            hourly_forecast = entity.hourly_forecast,
            daily_forecast = entity.daily_forecast
        )
    }

    override fun mapFromDomain(domain: DomainEntity): CacheEntity {
        return CacheEntity(
            id = domain.id,
            isLastSearchedLocation = domain.isLastSearchedLocation,
            name = domain.name,
            country = domain.country,
            lat = domain.lat,
            lon = domain.lon,
            lastUpdated = domain.lastUpdated,
            tempC = domain.tempC,
            feelslikeC = domain.feelslikeC,
            condition_txt = domain.condition_txt,
            condition_ic = domain.condition_ic,
            airQuality = domain.airQuality,
            uv = domain.uv,
            windKph = domain.windKph,
            windDir = domain.windDir,
            humidity = domain.humidity,
            visKm = domain.visKm!!,
            pressureIn = domain.pressureIn,
            maxtempC = domain.maxtempC,
            mintempC = domain.mintempC,
            dailyChanceOfRain = domain.dailyChanceOfRain,
            sunrise = domain.sunrise,
            sunset = domain.sunset,
            hourly_forecast = domain.hourly_forecast,
            daily_forecast = domain.daily_forecast
        )
    }

    override fun mapEntitiesList(list: List<CacheEntity>): List<DomainEntity> {
       return list.map { mapToDomain(it) }
    }

}














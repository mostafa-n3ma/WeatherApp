package com.example.weatherapp.operations.data_management.data_sources

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.weatherapp.operations.MY_API_KEY
import com.example.weatherapp.operations.data_management.data_entities.CacheEntity
import com.example.weatherapp.operations.data_management.data_entities.CacheMapper
import com.example.weatherapp.operations.data_management.data_entities.DomainEntity
import com.example.weatherapp.operations.data_management.data_entities.NetWorkMapper
import com.example.weatherapp.operations.data_management.data_sources.LocalDataSource.DefaultLocalDataSource
import com.example.weatherapp.operations.data_management.data_sources.RemoteDataSource.DefaultRemoteDataSource
import com.example.weatherapp.operations.data_management.data_utils.getCurrentDateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class Repository
@Inject
constructor(
    private val localDataSource: DefaultLocalDataSource,
    private val remoteDataSource: DefaultRemoteDataSource,
    private val cacheMapper: CacheMapper,
    private val netWorkMapper: NetWorkMapper
) {


    companion object {
        val TAG = "Repository"
    }

    // Local
    suspend fun insertCacheItem(domainEntity: DomainEntity) :Long {
        val cacheItem = cacheMapper.mapFromDomain(domainEntity)
        return localDataSource.insert(cacheItem)
    }

    suspend fun updateCacheItem(domainEntity: DomainEntity) {
        val cacheItem = cacheMapper.mapFromDomain(domainEntity)
        localDataSource.update(cacheItem)
    }

    suspend fun deleteCacheItem(domainEntity: DomainEntity) {
        val cacheItem = cacheMapper.mapFromDomain(domainEntity)
        localDataSource.delete(cacheItem)
    }

    suspend fun clearDatabase(){
        localDataSource.clearDataBase()
    }


    fun getSearchedLocationsList(): LiveData<List<DomainEntity>> {
        return localDataSource.getCaches().map { cacheEntities: List<CacheEntity> ->
            cacheMapper.mapEntitiesList(cacheEntities)
        }
    }

    fun getMainDisplay():LiveData<CacheEntity?>{
        return localDataSource.getMainDisplay()
    }



    // Remote
    suspend fun getLocationForecast(apiKey: String, location: String): DomainEntity {
        return netWorkMapper.mapToDomain(remoteDataSource.getWeatherForecast(apiKey, location))
    }





    suspend fun updateCacheData() {
        val currentDate: String = getCurrentDateTime()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val currentDateTime = LocalDateTime.parse(currentDate, formatter)

        val locations: List<DomainEntity> = cacheMapper.mapEntitiesList(localDataSource.getCaches().value?: listOf())
        locations.forEach { location ->
            val lastUpdatedDateTime = LocalDateTime.parse(location.lastUpdated, formatter)
            if (lastUpdatedDateTime.isBefore(currentDateTime)) {
                // The data is before the current date, so update the data
                val updatedItem: DomainEntity = getLocationForecast(MY_API_KEY, location.name!!)
                updatedItem.id = location.id
                updateCacheItem(updatedItem)
            }
        }
    }


}

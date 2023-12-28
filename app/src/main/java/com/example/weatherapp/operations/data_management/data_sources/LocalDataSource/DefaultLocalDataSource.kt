package com.example.weatherapp.operations.data_management.data_sources.LocalDataSource

import androidx.lifecycle.LiveData
import com.example.weatherapp.operations.data_management.data_entities.CacheEntity
import com.example.weatherapp.operations.data_management.data_utils.DataState
import kotlinx.coroutines.flow.Flow

interface DefaultLocalDataSource {
    suspend fun insert(cacheEntity: CacheEntity)

    suspend fun update (cacheEntity: CacheEntity)

    suspend fun delete(cacheEntity: CacheEntity)

    suspend fun clearDataBase()

     fun getCaches(): LiveData<List<CacheEntity>>

     fun getMainDisplay():LiveData<CacheEntity?>
}
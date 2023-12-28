package com.example.weatherapp.operations.data_management.data_sources.LocalDataSource

import androidx.lifecycle.LiveData
import com.example.weatherapp.operations.data_management.data_entities.CacheEntity
import com.example.weatherapp.operations.data_management.data_utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource
@Inject
constructor(private val cacheDoa: CacheDoa): DefaultLocalDataSource{
    override suspend fun insert(cacheEntity: CacheEntity) {
        cacheDoa.insertCacheItem(cacheEntity)
    }

    override suspend fun update(cacheEntity: CacheEntity) {
        cacheDoa.updateCache(cacheEntity)
    }

    override suspend fun delete(cacheEntity: CacheEntity) {
        cacheDoa.deleteCache(cacheEntity)
    }

    override suspend fun clearDataBase() {
        return cacheDoa.clearDatabase()
    }

    override  fun getCaches(): LiveData<List<CacheEntity>> {
        return cacheDoa.getAllCaches()
    }

    override fun getMainDisplay(): LiveData<CacheEntity?> {
        return cacheDoa.getMainDisplay()
    }
}
package com.example.weatherapp.operations.data_management.data_sources.LocalDataSource

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.operations.data_management.data_entities.CacheEntity
import com.example.weatherapp.operations.data_management.data_utils.CacheConverters
import kotlinx.coroutines.flow.Flow


@Dao
interface CacheDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCacheItem(cacheItem: CacheEntity)

    @Query("SELECT * FROM Caches")
    fun getAllCaches(): Flow<List<CacheEntity>>
}

@Database(entities = [CacheEntity::class], version = 1, exportSchema = false)
@TypeConverters(CacheConverters::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getCacheDao(): CacheDoa
}
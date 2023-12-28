package com.example.weatherapp.operations.data_management.data_sources.LocalDataSource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Update
import com.example.weatherapp.operations.data_management.data_entities.CacheEntity
import com.example.weatherapp.operations.data_management.data_utils.CacheConverters
import com.example.weatherapp.operations.data_management.data_utils.DataState
import kotlinx.coroutines.flow.Flow


@Dao
interface CacheDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCacheItem(cacheItem: CacheEntity)

    @Query("SELECT * FROM Caches")
    fun getAllCaches(): LiveData<List<CacheEntity>>

    @Update
    fun updateCache(cacheItem: CacheEntity)


    @Delete
    fun deleteCache(cacheItem: CacheEntity)

    @Query("SELECT * FROM Caches WHERE isLastSearchedLocation = 1")
    fun getMainDisplay():LiveData<CacheEntity?>

    @Query("DELETE FROM caches")
    suspend fun clearDatabase()
}

@Database(entities = [CacheEntity::class], version = 1, exportSchema = false)
@TypeConverters(CacheConverters::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getCacheDao(): CacheDoa
}
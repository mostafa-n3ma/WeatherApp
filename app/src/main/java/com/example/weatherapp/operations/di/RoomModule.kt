package com.example.weatherapp.operations.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.operations.data_management.data_sources.LocalDataSource.CacheDoa
import com.example.weatherapp.operations.data_management.data_sources.LocalDataSource.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): WeatherDatabase{
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather database"
        ).build()
    }


    @Singleton
    @Provides
    fun provideCacheDao(database: WeatherDatabase):CacheDoa{
        return database.getCacheDao()
    }


}
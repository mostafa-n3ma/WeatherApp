package com.example.weatherapp.operations.di

import com.example.weatherapp.operations.data_management.data_entities.CacheMapper
import com.example.weatherapp.operations.data_management.data_entities.NetWorkMapper
import com.example.weatherapp.operations.data_management.data_sources.LocalDataSource.LocalDataSource
import com.example.weatherapp.operations.data_management.data_sources.RemoteDataSource.DefaultRemoteDataSource
import com.example.weatherapp.operations.data_management.data_sources.RemoteDataSource.RemoteDataSource
import com.example.weatherapp.operations.data_management.data_sources.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{
    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        cacheMapper: CacheMapper,
        netWorkMapper: NetWorkMapper
    ):Repository{
        return Repository(localDataSource, remoteDataSource, cacheMapper, netWorkMapper)
    }
}
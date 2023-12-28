package com.example.weatherapp.presentation.screens

import android.service.autofill.Transformation
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.operations.MY_API_KEY
import com.example.weatherapp.operations.data_management.data_entities.CacheEntity
import com.example.weatherapp.operations.data_management.data_entities.CacheMapper
import com.example.weatherapp.operations.data_management.data_entities.DomainEntity
import com.example.weatherapp.operations.data_management.data_entities.NetWorkEntity
import com.example.weatherapp.operations.data_management.data_entities.NetWorkMapper
import com.example.weatherapp.operations.data_management.data_sources.RemoteDataSource.WeatherApiService
import com.example.weatherapp.operations.data_management.data_sources.Repository
import com.example.weatherapp.operations.data_management.data_utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers


@HiltViewModel
class WeatherViewModel
@Inject
constructor(
    private val repository: Repository,
    private val cacheMapper: CacheMapper,
    private val netWorkMapper: NetWorkMapper
):ViewModel(){
    companion object{
        val TAG: String = "WeatherViewModel"
    }

     val liveLocationList :LiveData<List<DomainEntity>> get() = repository.getSearchedLocationsList()

    val liveMainDisplayLocation:LiveData<DomainEntity?> get() = repository.getMainDisplay().map {cacheEntity ->
        if (cacheEntity != null) {
            cacheMapper.mapToDomain(cacheEntity)
        }    else{
            null
        }
    }


    fun clearDataBase(){
        viewModelScope.launch (Dispatchers.IO){
            repository.clearDatabase()
        }
    }




    init {



    }



}
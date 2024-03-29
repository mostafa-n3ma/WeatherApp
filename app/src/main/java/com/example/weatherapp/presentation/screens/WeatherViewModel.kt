package com.example.weatherapp.presentation.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.operations.MY_API_KEY
import com.example.weatherapp.operations.data_management.data_entities.CacheMapper
import com.example.weatherapp.operations.data_management.data_entities.DomainEntity
import com.example.weatherapp.operations.data_management.data_entities.NetWorkMapper
import com.example.weatherapp.operations.data_management.data_sources.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.map
import com.example.weatherapp.operations.data_management.data_utils.getCurrentDateTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.log


@HiltViewModel
class WeatherViewModel
@Inject
constructor(
    private val repository: Repository,
    private val cacheMapper: CacheMapper,
    private val netWorkMapper: NetWorkMapper
) : ViewModel() {
    companion object {
        val TAG: String = "WeatherViewModel"
    }

    val liveLocationList: LiveData<List<DomainEntity>> get() = repository.getSearchedLocationsList()
    lateinit var observedLocations: List<DomainEntity>

    private val _savedCurrentLocation = MutableLiveData<String?>()
    val savedCurrentLocation:LiveData<String?> get() = _savedCurrentLocation

    fun saveCurrentLocation(value: String?){
        _savedCurrentLocation.value = value
    }



    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog : LiveData<Boolean> get() = _showDialog
    enum class DialogStatus{Opened,Closed}
    fun dialogStatus(status:DialogStatus){
        when(status){
            DialogStatus.Opened -> {_showDialog.value = true}
            DialogStatus.Closed -> {_showDialog.value = false}
        }
    }

    fun currentLocationBtnClicked(){
        when(_savedCurrentLocation.value){
            null->{
                announceSnackBarMessage("cannot get current location check location Permissions")
            }
            else->{
               dialogStatus(DialogStatus.Opened)
            }
        }
    }


    fun dialogConfirmBtnClicked(){
        if (!_savedCurrentLocation.value.isNullOrEmpty()){
            searchCurrentLocation(_savedCurrentLocation.value.toString())
            dialogStatus(DialogStatus.Closed)
        }
    }


    private fun searchCurrentLocation(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result: DomainEntity = repository.getLocationForecast(MY_API_KEY, name)
                val isAvailable: DomainEntity? = observedLocations.find {
                    it.name == result.name
                }
                when(isAvailable){
                    null->{
                        Log.d(TAG, "search: the item is not existed before -> adding")
                        val databaseResult: Long = repository.insertCacheItem(result)
                        result.apply { id = databaseResult.toInt() }
                    }
                    else->{
                        Log.d(TAG, "search: the item is already existed -> updating")
                        result.id = isAvailable.id
                        repository.updateCacheItem(result)
                    }
                }

                delay(2000)
                setToMainDisplay(result)


            } catch (e: Exception) {
                Log.d(TAG, "search: error: ${e.message}")
            }
        }
    }




fun deleteCacheItem(domainEntity: DomainEntity){
    viewModelScope.launch (Dispatchers.IO){
        repository.deleteCacheItem(domainEntity)
    }
}






    private val _focusOnTextField = MutableLiveData<Boolean>()
    val focusOnTextField : LiveData<Boolean>get() = _focusOnTextField
    fun requestFocusOnTextField(value:Boolean){
        _focusOnTextField.value = value //true
    }





    private val _snackBarAnnouncement = MutableLiveData<String>()
    val snackBarAnnouncement: LiveData<String> get() = _snackBarAnnouncement
    fun announceSnackBarMessage(msg: String) {
        _snackBarAnnouncement.value = msg
        _snackBarAnnouncement.value = ""
    }


    private val _hideKeyBoard = MutableLiveData<Boolean>()
    val hideKeyBoard: LiveData<Boolean> get() = _hideKeyBoard

    fun hideKeBoard() {
        _hideKeyBoard.value = true
        _hideKeyBoard.value = false
    }


    private val _forecastType = MutableLiveData<ForecastType>()
    val forecastType: LiveData<ForecastType> get() = _forecastType

    fun changeForeCastTo(forecastType: ForecastType) {
        _forecastType.value = forecastType
    }


    init {
        _forecastType.value = ForecastType.hourly
        _hideKeyBoard.value = false
        _snackBarAnnouncement.value = ""
        _focusOnTextField.value = false
        _savedCurrentLocation.value = null
        _showDialog.value = false
    }


    fun getLocationAfterObservation(list: List<DomainEntity>) {
        observedLocations = list
    }

    val liveMainDisplayLocation: LiveData<DomainEntity?>
        get() = repository.getMainDisplay().map { cacheEntity ->
            if (cacheEntity != null) {
                cacheMapper.mapToDomain(cacheEntity)
            } else {
                null
            }
        }


     fun clearDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearDatabase()
        }
    }


    fun getDefaultDisplayLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            val defaultLocation: DomainEntity =
                repository.getLocationForecast(MY_API_KEY, "baghdad")
            defaultLocation.isMAinDisplay = true
            repository.insertCacheItem(defaultLocation)
        }
    }

    fun search(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result: DomainEntity = repository.getLocationForecast(MY_API_KEY, name)
                Log.d(TAG, "search: result from api $result")
                Log.d(TAG, "search: observedLocations = ${observedLocations}")
                val isAvailable: DomainEntity? = observedLocations.find {
                    it.name == result.name
                }
                when(isAvailable){
                   null->{
                        Log.d(TAG, "search: the item is not existed before -> adding")
                        val databaseResult: Long = repository.insertCacheItem(result)
                    }
                    else->{
                        Log.d(TAG, "search: the item is already existed -> updating")
                        result.id = isAvailable.id
                        repository.updateCacheItem(result)
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, "search: error: ${e.message}")
            }
        }
    }

    fun setToMainDisplay(domainEntity: DomainEntity) {
        Log.d(TAG, "setToMainDisplay: clicked entity : $domainEntity")
        Log.d(TAG, "setToMainDisplay: locationsList = ${observedLocations}")
        viewModelScope.launch(Dispatchers.IO) {
            observedLocations.map { location ->
                location.isMAinDisplay = location.id == domainEntity.id
                Log.d(TAG, "setToMainDisplay: $location : ${location.isMAinDisplay}")
                repository.updateCacheItem(location)
            }
        }
    }


    fun updateCacheData(observedLocations:List<DomainEntity>){
        viewModelScope.launch (Dispatchers.IO){
            val currentDate: String = getCurrentDateTime()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val currentDateTime = LocalDateTime.parse(currentDate, formatter)
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    observedLocations.forEach{location ->
                        val lastUpdatedDateTime = LocalDateTime.parse(location.lastUpdated, formatter)
                        if (lastUpdatedDateTime.isBefore(currentDateTime)){
                            val updatedItem: DomainEntity = repository.getLocationForecast(MY_API_KEY, location.name!!)
                            updatedItem.id = location.id
                            updatedItem.isMAinDisplay = location.isMAinDisplay
                            repository.updateCacheItem(updatedItem)
                        }

                    }
                }catch (e:Exception){
                    Log.d(TAG, "updateCacheData: errer: ${e.message}")
                }
            }

        }
    }


}

enum class ForecastType {
    hourly, daily
}
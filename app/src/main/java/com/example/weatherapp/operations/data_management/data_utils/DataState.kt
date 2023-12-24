package com.example.weatherapp.operations.data_management.data_utils

sealed class DataState<out R>{
    data class Success<out T>(val data:T):DataState<T>()
    data class Error(val exception:Exception):DataState<Nothing>()
    object Loading:DataState<Nothing>()
}

package com.example.weatherapp.operations.data_management.data_utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentDateTime(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val date = Date(System.currentTimeMillis())
    return dateFormat.format(date)
}



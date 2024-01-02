package com.example.weatherapp.operations.data_management.data_utils


import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar


fun getCurrentDateTime(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val date = Date(System.currentTimeMillis())
    return dateFormat.format(date)
}


fun getShortDayOfWeekFromDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date: Date = inputFormat.parse(dateString) ?: return "Invalid Date"

    val dayFormat = SimpleDateFormat("EEE", Locale.getDefault())
    return dayFormat.format(date).uppercase(Locale.getDefault())
}



fun isCurrentDayEEE(): String {
    return LocalDate.now().format(DateTimeFormatter.ofPattern("EEE", Locale.getDefault())).uppercase()
}



fun getTimeFormattedWithHourCondition(timeString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val date: Date = inputFormat.parse(timeString) ?: return "Invalid Time"

    val currentTime = Calendar.getInstance()
    val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
    val timeHour = Calendar.getInstance()
    timeHour.time = date
    val hour = timeHour.get(Calendar.HOUR_OF_DAY)

    return if (currentHour == hour) {
        "Now"
    } else {
        val outputFormat = SimpleDateFormat("h a", Locale.getDefault())
        outputFormat.format(date)
    }
}







fun getImageNameFromUrl(url: String): String {
    return if (url.length > 6) {
        url.substring(url.length - 11)
    } else {
        url // Return the whole string if it's 6 characters or less
    }
}












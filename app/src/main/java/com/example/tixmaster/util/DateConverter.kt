package com.example.tixmaster.util

import android.content.Context
import androidx.core.os.ConfigurationCompat
import java.text.SimpleDateFormat
import java.util.*

fun String.convertDate(context: Context): String? {
    return try {
        val currentLocale = ConfigurationCompat.getLocales(context.resources.configuration)[0]
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("America/Chicago")
        val passedDate: Date = inputFormat.parse(this)

        //Here you put how you want your date to be, this looks like this Tue,Nov 2, 2021, 12:23 pm
        val outputFormatDay = SimpleDateFormat("EEE d MMM,", currentLocale)
        outputFormatDay.timeZone = TimeZone.getDefault()
        val newDateString = outputFormatDay.format(passedDate)
        newDateString
    } catch (_: Exception) {
        "00:00:00"
    }

}

fun String.convertTime(context: Context): String? {
    return try {
        val currentLocale = ConfigurationCompat.getLocales(context.resources.configuration)[0]
        val inputFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("America/Chicago")
        val passedDate: Date = inputFormat.parse(this)

        //Here you put how you want your date to be, this looks like this Tue,Nov 2, 2021, 12:23 pm
        val outputFormatTime = SimpleDateFormat("hh:mm aaa", currentLocale)
        outputFormatTime.timeZone = TimeZone.getDefault()
        val newTimeString = outputFormatTime.format(passedDate)
        newTimeString
    } catch (_: Exception) {
        "00:00:00"
    }

}
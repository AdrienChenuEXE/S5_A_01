package com.example.application_s5_a_01.data.enums

import com.example.application_s5_a_01.model.Days
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale

val calendar: Calendar = Calendar.getInstance()

enum class Interval (
    val text: String,
    val interval: String,
    val secondsToRemove: Int,
    val getLabels: List<String>
) {
    hour(text = "1h", interval = "10m", secondsToRemove = 3600, getHourLabels()), // 1h
    halfday(text = "12h", interval = "1h", secondsToRemove = 3600 * 12, getHalfDayLabels()), // 12h
    day(text = "24h", interval = "2h", secondsToRemove = 3600*24, getDayLabels()), // 24h
    week(text = "7d", interval = "1d", secondsToRemove = 3600*24*7, getWeekLabels()), // 7 days

}

fun getFormat(pattern: String): Int {
    val date: Date = calendar.time
    return SimpleDateFormat(pattern, Locale.FRANCE).format(date.time).toInt()
}

fun getDayLabels(): List<String> {
    val currentHour = LocalDateTime.now().hour
    return List(12) {
        val hour = (currentHour - it * 2) % 24
        val formattedHour = if (hour < 0) hour + 24 else hour
        "$formattedHour" + "h"
    }.reversed()
}

fun getHalfDayLabels(): List<String> {
    val currentHour = LocalDateTime.now().hour
    return List(12) {
        val hour = (currentHour - it) % 24
        val formattedHour = if (hour < 0) hour + 24 else hour
        "$formattedHour" + "h"
    }.reversed()
}


fun getHourLabels(): List<String> {
    return listOf("-60m", "-50m", "-40m","-30m", "-20m", "-10m", "-0m")
}

fun getWeekLabels(): List<String> {
    val currentDay = getFormat("u")
    val labelList: ArrayList<String> = ArrayList()
    Days.forEachIndexed { index, _ ->
        labelList.add(Days[((currentDay + index) % 7)])
    }
    return labelList
}


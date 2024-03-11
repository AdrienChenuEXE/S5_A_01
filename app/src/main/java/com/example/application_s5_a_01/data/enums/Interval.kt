package com.example.application_s5_a_01.data.enums

enum class Interval (
    val text: String,
    val interval: String,
    val secondsToRemove: Int,
) {
    week(text = "7d", interval = "1d", secondsToRemove = 3600*24*7), // 7 days
    day(text = "24h", interval = "2h", secondsToRemove = 3600*24), // 24h
    halfday(text = "12h", interval = "1h", secondsToRemove = 3600 * 12), // 12h
    hour(text = "1h", interval = "5m", secondsToRemove = 3600), // 1h
}


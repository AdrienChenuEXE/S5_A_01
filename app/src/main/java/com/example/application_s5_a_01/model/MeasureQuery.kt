package com.example.application_s5_a_01.model

data class MeasureQuery (
    val startTimeStamp:Int,
    val endTimeStamp: Int,
    val interval: String,
    val measureList: List<String>,
    val discomfortList: List<String>,
    val salle: String
)
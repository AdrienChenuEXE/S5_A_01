package com.example.application_s5_a_01.model

import kotlinx.serialization.Serializable

@Serializable
data class MeasuresData(
    val data: ArrayList<Salle>
) {
    fun toPoints() {
        data[0].values
    }
}

@Serializable
data class Salle(
    val salle: String,
    val values : ArrayList<TimePoint>
)

@Serializable
data class TimePoint(
    val time: String,
    val values: ArrayList<Measure>
)

@Serializable
data class Measure (
    val discomfortList: ArrayList<String>,
    val mesure: String,
    val value: Double
)
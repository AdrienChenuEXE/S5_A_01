package com.example.application_s5_a_01.model

import kotlinx.serialization.Serializable

@Serializable
data class Salle(
    val salle: String,
    val values : List<TimePoint>
)

@Serializable
data class TimePoint(
    val time: String,
    val values: List<Measure>
)

@Serializable
data class Measure (
    val discomfortList: String,
    val mesure: List<String>,
    val value: Double
)
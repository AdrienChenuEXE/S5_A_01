package com.example.application_s5_a_01.model

import kotlinx.serialization.Serializable

@Serializable
data class SallesList(
    val salles: ArrayList<Salle>,
)

@Serializable
data class Salle(
    val id: String,
    val timestamps : ArrayList<MeasuresList>
)

@Serializable
data class MeasuresList (
    val timestamp: Int,
    val measure: ArrayList<Measure>
)

@Serializable
data class Measure(
    val discomfortList: ArrayList<String>,
    val value: Double
)
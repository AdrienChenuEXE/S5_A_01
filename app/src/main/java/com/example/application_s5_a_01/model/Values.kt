package com.example.application_s5_a_01.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Values(
    val discomfortList: List<String>,
    val measurement: String,
    val sensorId: String,
    val value: Float
)

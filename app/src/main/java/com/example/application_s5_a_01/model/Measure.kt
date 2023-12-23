package com.example.application_s5_a_01.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Measure(
    val timestamp: Int,
    val values: Values
)

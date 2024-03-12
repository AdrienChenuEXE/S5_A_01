package com.example.application_s5_a_01.model

import com.example.application_s5_a_01.R

enum class MeasureValues(
    val value: String,
    val unity: String?,
    val icon: Int? = R.drawable.ic_connection_error
    ) {
    humidity("Humidité", "%"),
    temperature("Température", "°C", R.drawable.temperature),
    smoke("Fumée", "?"),
    sound("Décibels", "dB", R.drawable.sound),
    luminosity("Luminosité", "lux", R.drawable.sun),
    carbondioxyde("CO2", "μg/m³", R.drawable.co2),
}

data class SingleMeasure(
    val measureValue: MeasureValues,
    val value: Double
)

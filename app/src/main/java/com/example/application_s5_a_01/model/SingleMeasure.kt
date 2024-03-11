package com.example.application_s5_a_01.model

enum class MeasureValues(
    val value: String,
    val unity: String?,
    ) {
    humidity("Humidité", "%"),
    temperature("Température", "°C"),
    smoke("Fumée", "?" ),
    sound("Décibels", "dB"),
    luminosity("Luminosité", "lux"),
    carbondioxyde("Co2", "μg/m³"),
    /*volatilcarbondioxyde("Co2 Volatile", "ug/m3"),
    dewcarbondioxyde("co2 Dew", "ug/m3\""),*/
}

data class SingleMeasure(
    val measureValue: MeasureValues,
    val value: Double
)

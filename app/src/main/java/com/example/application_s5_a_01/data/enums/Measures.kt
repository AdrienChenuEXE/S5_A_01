package com.example.application_s5_a_01.data.enums

enum class Measures(
    val text: String,
    val value: String,
    val unity: String,
    val roundTo: Int
) {
    co2(text = "CO2", "Co2", "μg/m³", 100),
    humidity(text = "Humidité", value = "Humidité", "%", 10 ),
    uv(text = "UV", value = "Ultra violets", "uv", 10),
    db(text = "dB", "Décibels", "dB", 5),
    temperature(text = "Temperature", "Température", "°C", 5),
    luminosite(text= "Luminosité", "Luminosité", "lux", 100)
}

data class SingleMeasure(
    val measure: Measures,
    val value: Double
)
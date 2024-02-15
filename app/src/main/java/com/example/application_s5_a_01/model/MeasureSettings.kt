package com.example.application_s5_a_01.model

import com.example.application_s5_a_01.R
import java.time.Instant

enum class ClassRoom(val text: String, val description: String?, val image: Int?) {
    D251("d251", "Description for D251", R.drawable.classroom1),
    D351("d351", "Description for D351", R.drawable.classroom1),
    D360("d360", "Description for D360", R.drawable.classroom1),
}

enum class Interval (val text: String) {
    day(text = "Dernier jour"), // 24h
    halfday(text = "Dernières 12h"), // 12h
    hour(text = "Dernière heure"), // 1h
}

enum class Measures (val text: String) {
    co2(text = "CO2"),
    humidity(text = "Humidité"),
    uv(text = "UV"),
    db(text = "dB"),
    temperature(text = "Temperature")
}

data class MeasureSettings (
    var classRoom: ClassRoom? = null,
    var discomforts: ArrayList<Measures> = arrayListOf(),
    var measure: Measures = Measures.co2,
    var interval: Interval = Interval.day
) {
    fun toMeasureQuery():MeasureQuery {
        var start = Instant.now().minusSeconds(3600).toEpochMilli()
        var intervalQ = "2h"
        var salle = classRoom?.text ?: ""

        if (interval == Interval.halfday) {
            start = Instant.now().minusSeconds(12 * 3600).toEpochMilli()
            intervalQ = "1h"
        } else if (interval == Interval.hour) {
            start = Instant.now().minusSeconds(24 * 3600).toEpochMilli()
            intervalQ = "5m"
        }

        return MeasureQuery(
            start = start,
            end = Instant.now().toEpochMilli(),
            salle = salle,
            interval = intervalQ,
            measure = measure.text
        )
    }
}
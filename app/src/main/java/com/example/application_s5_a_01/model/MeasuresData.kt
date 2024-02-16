package com.example.application_s5_a_01.model

import com.example.application_s5_a_01.R

enum class Bucket(val text:String) {
    IUT("IUT_BUCKET"),
    TETRAS("POCHATSA_BUCKET")
}

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
    var bucket: Bucket = Bucket.IUT,
    var classRoom: ClassRoom? = null,
    var discomforts: ArrayList<Measures> = arrayListOf(),
    var measure: Measures = Measures.co2,
    var interval: Interval = Interval.day
) {
    fun toMeasureQuery():MeasureQuery {
        val currentTime = System.currentTimeMillis()/1000
        var start = currentTime - 3600
        var intervalQ = "2h"
        val salle = classRoom?.text ?: ""

        if (interval == Interval.halfday) {
            start = currentTime - 3600 * 12
            intervalQ = "1h"
        } else if (interval == Interval.hour) {
            start = currentTime - 3600  * 24
            intervalQ = "5m"
        }

        return MeasureQuery(
            start = start,
            end = currentTime,
            salle = salle,
            interval = intervalQ,
            measure = measure.text
        )
    }
}
package com.example.application_s5_a_01.model

import com.example.application_s5_a_01.R
import com.example.application_s5_a_01.data.enums.Interval

enum class Bucket(val text:String) {
    IUT("IUT_BUCKET"),
    TETRAS("POCHATSA_BUCKET")
}

enum class ClassRoom(val text: String, val description: String?, val image: Int?) {
    D251("d251", "Description for D251", R.drawable.classroom1),
    D351("d351", "Description for D351", R.drawable.classroom1),
    D360("d360", "Description for D360", R.drawable.classroom1),
}

interface ValueEntry {
    val label: String
}

val Days = listOf("Lu", "Ma", "Me", "Je", "Ve", "Sa", "Di")

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
    var interval: Interval = Interval.week
) {

    fun toMeasureQuery():MeasureQuery {
        val currentTime = System.currentTimeMillis()/1000
        val salle = classRoom?.text ?: ""

        return MeasureQuery(
            start = currentTime - interval.secondsToRemove,
            end = currentTime,
            salle = salle,
            interval = interval.interval,
            measure = measure.text
        )
    }
}
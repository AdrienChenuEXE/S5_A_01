package com.example.application_s5_a_01.model

import com.example.application_s5_a_01.data.enums.Bucket
import com.example.application_s5_a_01.data.enums.ClassRoom
import com.example.application_s5_a_01.data.enums.Interval
import com.example.application_s5_a_01.data.enums.Measures

val Days = listOf("Lu", "Ma", "Me", "Je", "Ve", "Sa", "Di")

data class MeasureSettings (
    var bucket: Bucket = Bucket.IUT,
    var classRoom: ClassRoom? = ClassRoom.D351,
    var discomforts: ArrayList<Measures> = arrayListOf(),
    var measure: Measures = Measures.humidity,
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
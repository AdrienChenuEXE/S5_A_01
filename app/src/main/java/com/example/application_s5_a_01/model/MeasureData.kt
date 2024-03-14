package com.example.application_s5_a_01.model

import com.example.application_s5_a_01.data.enums.Measures
import com.example.application_s5_a_01.data.enums.SingleMeasure
import kotlinx.serialization.Serializable

@Serializable
data class MeasuresData(
    val data: ArrayList<Salle>
) {
    private fun getSalle(): Salle {
        return data[0]
    }
    fun getValues(mesure: String):List<Double> {
        return getSalle().getMeasureValuesList(mesure).dropLast(1)
    }

    fun getCurrentDiscomforts(): ArrayList<String> {
        val values = getSalle().getLastTimePoint().values
        val discomfortsList = arrayListOf<String>()
        for (value in values) {
            for (discomfort in value.discomfortList) {
                discomfortsList.add(discomfort)
            }
        }
        return discomfortsList
    }

   fun getCurrentValues(): ArrayList<SingleMeasure> {
       val currentValues = getSalle().getLastTimePoint().values
       val currentMeasures = arrayListOf<SingleMeasure>()
       Measures.entries.forEach { measure ->
           val value = currentValues.find { it.mesure == measure.value }?.value
           if (value != null) {
               currentMeasures.add(
                    SingleMeasure(
                        measure = measure,
                        value = value
                    )
               )
           }
       }
       return currentMeasures
   }
}

@Serializable
data class Salle(
    val salle: String,
    val values : ArrayList<TimePoint>
) {
    fun getMeasureValuesList(mesure: String): List<Double> {
        return values.map {
            it.getMeasureValue(mesure)
        }
    }

    fun getLastTimePoint():TimePoint {
        return values.last()
    }
}

@Serializable
data class TimePoint(
    val time: String,
    val values: ArrayList<Measure>
) {
    fun getMeasureValue(mesure: String): Double {
        return values.find { it.mesure == mesure }?.value ?: 0.0
    }
}

@Serializable
data class Measure (
    val discomfortList: ArrayList<String>,
    val mesure: String,
    val value: Double
)

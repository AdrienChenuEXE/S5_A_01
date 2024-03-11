package com.example.application_s5_a_01.model

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

   fun getCurrentValues(): ArrayList<SingleMeasure> {
       val currentValues = getSalle().getLastTimePoint().values
       val currentMeasures = arrayListOf<SingleMeasure>()
       MeasureValues.entries.forEach { measure ->
           val value = currentValues.find { it.mesure == measure.value }?.value
           if (value != null) {
               currentMeasures.add(
                    SingleMeasure(
                        measureValue = measure,
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
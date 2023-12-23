package com.example.application_s5_a_01.data

import com.example.application_s5_a_01.model.Measure
import com.example.application_s5_a_01.network.MeasureApiService
import retrofit2.http.Query

interface MeasureRepository {
    suspend fun getMeasures(startTimeStamp:Int,
                            endTimeStamp: Int,
                            interval: String,
                            measure: List<String>,
                            discomfortList: List<String>,
                            salle: String): List<Measure>
}

class NetworkMeasureRepository(
    private val measureApiService: MeasureApiService
) : MeasureRepository {

    override suspend fun getMeasures(startTimeStamp:Int,
                                     endTimeStamp: Int,
                                     interval: String,
                                     measure: List<String>,
                                     discomfortList: List<String>,
                                     salle: String
    ): List<Measure> = measureApiService.getData(
                                            startTimeStamp,
                                            endTimeStamp,
                                            interval,
                                            measure,
                                            discomfortList,
                                            salle
    )
}

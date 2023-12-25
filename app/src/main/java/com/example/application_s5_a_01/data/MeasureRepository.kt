package com.example.application_s5_a_01.data

import com.example.application_s5_a_01.model.Measure
import com.example.application_s5_a_01.model.MeasureQuery
import com.example.application_s5_a_01.network.MeasureApiService

interface MeasureRepository {
    suspend fun getMeasures(measureQuery: MeasureQuery): List<Measure>
}

class NetworkMeasureRepository(
    private val measureApiService: MeasureApiService
) : MeasureRepository {
    override suspend fun getMeasures(measureQuery: MeasureQuery): List<Measure> = measureApiService.getData(
        measureQuery
    )
}

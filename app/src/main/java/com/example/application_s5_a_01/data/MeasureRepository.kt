package com.example.application_s5_a_01.data

import com.example.application_s5_a_01.model.MeasureQuery
import com.example.application_s5_a_01.model.MeasuresData
import com.example.application_s5_a_01.network.MeasureApiService

interface MeasureRepository {
    suspend fun getMeasures(measureQuery: MeasureQuery): MeasuresData
}

class NetworkMeasureRepository(
    private val measureApiService: MeasureApiService
) : MeasureRepository {
    override suspend fun getMeasures(measureQuery: MeasureQuery): MeasuresData = measureApiService.getData(
        bucket = measureQuery.bucket,
        discomforts = null,
        end = measureQuery.end,
        start = measureQuery.start,
        interval = measureQuery.interval,
        measures = "temperature"
    )
}

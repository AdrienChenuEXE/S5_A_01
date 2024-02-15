package com.example.application_s5_a_01.data

import com.example.application_s5_a_01.model.MeasureQuery
import com.example.application_s5_a_01.model.Salle
import com.example.application_s5_a_01.network.MeasureApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

interface MeasureRepository {
    suspend fun getMeasures(measureQuery: MeasureQuery): List<Salle>
}

class NetworkMeasureRepository(
    private val measureApiService: MeasureApiService
) : MeasureRepository {
    override suspend fun getMeasures(measureQuery: MeasureQuery): List<Salle> = measureApiService.getData(
        measureQuery.toString().toRequestBody("application/json".toMediaTypeOrNull())
    )
}

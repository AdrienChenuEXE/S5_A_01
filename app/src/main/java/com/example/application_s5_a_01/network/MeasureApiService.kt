package com.example.application_s5_a_01.network

import com.example.application_s5_a_01.model.Measure
import com.example.application_s5_a_01.model.MeasureQuery
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface MeasureApiService {
    @GET("/data")
    suspend fun getData(@Body measureQuery: MeasureQuery): List<Measure>
}
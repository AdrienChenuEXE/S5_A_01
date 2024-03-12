package com.example.application_s5_a_01.network

import com.example.application_s5_a_01.model.MeasuresData
import retrofit2.http.GET
import retrofit2.http.Query

interface MeasureApiService {
    @GET("data")
    suspend fun getData(
        @Query("bucket") bucket: String,
        @Query("start") start: Long,
        @Query("end") end: Long,
        @Query("interval") interval: String,
        @Query("discomfort") discomforts: String?,
        @Query("measure") measures: String?,
        @Query("salle") salle: String?
    ): MeasuresData
}


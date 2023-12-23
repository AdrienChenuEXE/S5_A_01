package com.example.application_s5_a_01.network

import com.example.application_s5_a_01.model.Measure
import retrofit2.http.GET
import retrofit2.http.Query

interface MeasureApiService {
    @GET("/data")
    suspend fun getData(
        @Query("start") startTimeStamp:Int,
        @Query("end") endTimeStamp: Int,
        @Query("interval") interval: String,
        @Query("measures") measure: List<String>,
        @Query("discomfort") discomfortList: List<String>,
        @Query("salle") salle: String
    ): List<Measure>
}
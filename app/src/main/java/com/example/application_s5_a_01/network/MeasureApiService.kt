package com.example.application_s5_a_01.network

import com.example.application_s5_a_01.model.Salle
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.HTTP

interface MeasureApiService {
    @HTTP(method = "GET", path ="/data", hasBody = true)
    suspend fun getData(@Body measureQuery: RequestBody): List<Salle>
}


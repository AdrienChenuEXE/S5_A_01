package com.example.application_s5_a_01.data

import com.example.application_s5_a_01.network.MeasureApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

interface AppContainer {
    val appRepository: MeasureRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "http://localhost:5000/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: MeasureApiService by lazy {
        retrofit.create(MeasureApiService::class.java)
    }

    override val appRepository: MeasureRepository by lazy {
        NetworkMeasureRepository(retrofitService)
    }
}

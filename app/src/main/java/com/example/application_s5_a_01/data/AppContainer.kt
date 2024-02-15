package com.example.application_s5_a_01.data

import com.example.application_s5_a_01.network.MeasureApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val appRepository: MeasureRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "http://localhost:5000/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService: MeasureApiService by lazy {
        retrofit.create(MeasureApiService::class.java)
    }

    override val appRepository: MeasureRepository by lazy {
        NetworkMeasureRepository(retrofitService)
    }
}

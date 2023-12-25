package com.example.application_s5_a_01

import android.app.Application
import com.example.application_s5_a_01.data.AppContainer
import com.example.application_s5_a_01.data.DefaultAppContainer

class SAEApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}

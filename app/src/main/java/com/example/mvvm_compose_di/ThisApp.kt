package com.example.mvvm_compose_di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ThisApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initialization()
    }

    private fun initialization() {
    }
}

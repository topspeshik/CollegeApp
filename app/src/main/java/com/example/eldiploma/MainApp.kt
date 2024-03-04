package com.example.eldiploma

import android.app.Application
import com.example.eldiploma.di.ApplicationComponent
import com.example.eldiploma.di.DaggerApplicationComponent

class MainApp: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}
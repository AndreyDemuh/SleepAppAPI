package com.example.sleepappapi

import android.app.Application
import com.example.sleepappapi.DataBase.HeroDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){

    override fun onCreate() {
        super.onCreate()
        HeroDataBase.initDB(applicationContext)
    }
}
package com.example.sleepappapi

import android.app.Application
import com.example.sleepappapi.DataBase.HeroDataBase
import com.example.sleepappapi.di.ApplicationComponent
import com.example.sleepappapi.di.ApplicationModule
import com.example.sleepappapi.di.DaggerApplicationComponent
import com.example.sleepappapi.network.Network

class App : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        HeroDataBase.initDB(applicationContext)
        applicationComponent = DaggerApplicationComponent
            .builder()
            .network(Network())
            .applicationModule(ApplicationModule(this.applicationContext))
            .build()
    }
}
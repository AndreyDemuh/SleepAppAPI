package com.example.sleepappapi

import android.app.Application
import com.example.sleepappapi.di.appModule
import com.example.sleepappapi.ui.allHeroes.allHeroModule
import com.example.sleepappapi.ui.oneHero.oneHeroModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
//        HeroDataBase.initDB(applicationContext)
//        applicationComponent = DaggerApplicationComponent
//            .builder()
//            .network(Network())
//            .applicationModule(ApplicationModule(this.applicationContext))
//            .build()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, allHeroModule, oneHeroModule))
        }
    }
}
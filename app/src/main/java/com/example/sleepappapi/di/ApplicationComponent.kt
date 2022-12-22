package com.example.sleepappapi.di

import com.example.sleepappapi.network.Network
import com.example.sleepappapi.ui.allBase.AllDisneyHeroFragment
import com.example.sleepappapi.ui.hero.OneHeroCardFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [Network::class, ApplicationModule::class])
interface ApplicationComponent {

    fun injectAllHero(fragment: AllDisneyHeroFragment)
    fun injectOneHero(fragment: OneHeroCardFragment)
}
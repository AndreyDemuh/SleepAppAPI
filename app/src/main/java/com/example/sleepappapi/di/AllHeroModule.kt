package com.example.sleepappapi.ui.allHeroes

import com.example.sleepappapi.repository.HeroDataSource
import com.example.sleepappapi.repository.HeroesRepository
import com.example.sleepappapi.ui.allBase.AllCharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val allHeroModule = module {
    viewModel {
        AllCharactersViewModel(repository = get(), heroDataSource = get())
    }

    factory<HeroesRepository> {
        HeroesRepository(api = get())
    }

    factory<HeroDataSource> {
        HeroDataSource(repository = get())
    }
}
package com.example.sleepappapi.ui.oneHero

import com.example.sleepappapi.ui.hero.OneHeroViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val oneHeroModule = module {

    viewModel {
        OneHeroViewModel(repository = get())
    }
}
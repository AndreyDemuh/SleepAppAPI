package com.example.sleepappapi.ui.oneHero

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.sleepappapi.repository.HeroesRepository
import com.example.sleepappapi.ui.hero.OneHeroViewModel
import javax.inject.Inject

class OneHeroViewModelFactory @Inject constructor(
    private val repository: HeroesRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return OneHeroViewModel(
            repository
        ) as T
    }
}
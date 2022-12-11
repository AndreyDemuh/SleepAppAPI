package com.example.sleepappapi.ui.allHeroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.sleepappapi.repository.HeroDataSource
import com.example.sleepappapi.repository.HeroesRepository
import com.example.sleepappapi.ui.allBase.AllCharactersViewModel
import javax.inject.Inject

class AllHeroesViewModelFactory @Inject constructor(
    private val repository: HeroesRepository, private val heroDataSource: HeroDataSource
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return AllCharactersViewModel(
            repository, heroDataSource
        ) as T
    }
}

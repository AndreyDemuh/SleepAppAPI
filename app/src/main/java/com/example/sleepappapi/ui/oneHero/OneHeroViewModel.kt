package com.example.sleepappapi.ui.hero

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sleepappapi.Hero
import com.example.sleepappapi.repository.HeroesRepository
import com.example.sleepappapi.utils.toHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OneHeroViewModel @Inject constructor(
    private val repository: HeroesRepository
) : ViewModel() {

    val oneHero = MutableLiveData<Hero>()

    var isHeroFavourite = MutableLiveData(false)

    fun getInfoOneHero(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getImageDisneyHero(id)
            if (response.isSuccessful) {
                oneHero.postValue((response.body()?.toHero()))
            } else {
                response.errorBody()
            }
        }
    }

    fun addFavouriteHero(hero: Hero) {
        viewModelScope.launch(Dispatchers.IO) {
            hero.isFavourite = true
            repository.insertHeroToFavourite(hero)
        }
    }

    fun deleteFavouriteHero(hero: Hero) {
        viewModelScope.launch(Dispatchers.IO) {
            hero.isFavourite = false
            repository.deleteHeroFromFavourite(hero)
        }
    }

    fun chooseHeroFavourite(hero: Hero) {
        if (isHeroFavourite.value == true) {
            deleteFavouriteHero(hero)
        } else {
            addFavouriteHero(hero)
        }
        isHeroFavourite.value = !(isHeroFavourite.value ?: true)
    }
}


package com.example.sleepappapi.ui.hero

import androidx.lifecycle.*
import com.example.sleepappapi.Hero
import com.example.sleepappapi.model.CharactersHero
import com.example.sleepappapi.repository.HeroesRepository
import com.example.sleepappapi.utils.toHero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OneHeroViewModel (
    private val repository: HeroesRepository
) : ViewModel() {

    val imageHeroUrl = MutableLiveData<Hero>()

    var isHeroFavourite = MutableLiveData<Boolean>(false)


    fun getImageOneHeroInfo(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getImageDisneyHero(id)
            if (response.isSuccessful) {
                imageHeroUrl.postValue((response.body()?.toHero()))
            } else {
                response.errorBody()
            }
        }
    }

    fun addHeroToFavourite(hero: CharactersHero) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertHeroToFavourite(hero)
        }
    }

    fun deleteHeroFromFavourite(hero: CharactersHero) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHeroFromFavourite(hero)
        }
    }

    fun chooseHeroFavourite() {
        if (isHeroFavourite.value == true) {

        } else {
            //code add
        }
        isHeroFavourite.value = !(isHeroFavourite.value ?: false)
    }
}


package com.example.sleepappapi.ui.allBase

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.sleepappapi.model.CharactersHero
import com.example.sleepappapi.repository.HeroDataSource
import com.example.sleepappapi.repository.HeroesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AllCharactersViewModel (
    private val repository: HeroesRepository, private val heroDataSource: HeroDataSource
) : ViewModel() {

    var onError: (() -> Unit)? = null

    val listHero = MutableLiveData<ArrayList<CharactersHero>>()

    val listFavouriteHero = MutableLiveData<ArrayList<CharactersHero>>()

    val flowHero = Pager(
        PagingConfig(pageSize = 10)
    ) {
        heroDataSource
    }.flow
        .cachedIn(viewModelScope)


    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        onError?.invoke()
    }


    fun getDisneyHeroCharacters() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = repository.getAllHeroes(
                1,
                10
            ) //ТУТ НАВЕРНО НЕ СОВСЕМ ПРАВИЛЬНО ПЕРЕДАВАТЬ НА ПРЯМУЮ АРГУМЕНТЫ??
            if (response.isSuccessful) {
                listHero.postValue(
                    (response.body()?.data ?: arrayListOf()) as ArrayList<CharactersHero>?
                )
            } else {
                onError?.invoke()
            }
        }
    }

    fun getListFavouriteHero() {
        viewModelScope.launch(Dispatchers.IO) {
            listFavouriteHero.postValue(repository.getListFavouriteHeroes() as ArrayList<CharactersHero>?)
        }
    }

    fun addFavouriteHero(hero: CharactersHero) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertHeroToFavourite(hero)
        }
    }

    fun deleteFavouriteHero(hero: CharactersHero) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHeroFromFavourite(hero)
        }
    }
}


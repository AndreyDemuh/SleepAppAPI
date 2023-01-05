package com.example.sleepappapi.ui.allBase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.sleepappapi.Hero
import com.example.sleepappapi.model.CharactersHero
import com.example.sleepappapi.repository.HeroDataSource
import com.example.sleepappapi.repository.HeroesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCharactersViewModel @Inject constructor(
    private val repository: HeroesRepository, private val heroDataSource: HeroDataSource
) : ViewModel() {

    var onError: (() -> Unit)? = null

    val listHero = MutableLiveData<ArrayList<CharactersHero>>()

    val listFavouriteHero = MutableLiveData<List<Hero>>()

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

    fun getAllDisneyHero() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = repository.getAllHeroes(1, 10)
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
        viewModelScope.launch {
            listFavouriteHero.value = repository.getFavouriteHeroesBoolean()
        }
    }
}


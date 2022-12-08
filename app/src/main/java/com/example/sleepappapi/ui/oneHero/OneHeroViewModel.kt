package com.example.sleepappapi.ui.hero

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sleepappapi.repository.HeroesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OneHeroViewModel @Inject constructor(
    private val repository: HeroesRepository
) : ViewModel() {

    val imageHeroUrl = MutableLiveData<String>()

    var isHeroFavourite = MutableLiveData<Boolean>(false)


    fun getImageOneHeroInfo(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getImageDisneyHero(id)
            if (response.isSuccessful) {
                Log.d(
                    "MyLog",
                    "getImageOneHeroInfo ${response.body()?.data?.firstOrNull()?.imageUrl.toString()}"
                )
                imageHeroUrl.postValue((response.body()?.data?.firstOrNull()?.imageUrl.toString()))
            } else {
                response.errorBody()
            }
        }
    }

    fun chooseHeroFavourite() {
        if (isHeroFavourite.value == true) {
            //code add
        } else {
            //code add
        }
        isHeroFavourite.value = !(isHeroFavourite.value ?: false)
    }
}


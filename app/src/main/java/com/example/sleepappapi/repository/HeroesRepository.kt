package com.example.sleepappapi.repository

import com.example.sleepappapi.DataBase.HeroDataBase
import com.example.sleepappapi.model.CharactersHero
import com.example.sleepappapi.network.DisneyHeroApi
import retrofit2.Response
import javax.inject.Inject


class HeroesRepository @Inject constructor(private val api: DisneyHeroApi) {

    suspend fun getAllHeroes(page: Int, limit: Int) =
        api.getHeroes(page, limit)

    suspend fun getImageDisneyHero(id: String): Response<CharactersHero> {
        return api.getImageHero(id)
    }

    suspend fun insertHeroToFavourite(hero: CharactersHero) {
        HeroDataBase.HeroDB.heroDao().insertHero(hero)
    }

    suspend fun deleteHeroFromFavourite(hero: CharactersHero) {
        HeroDataBase.HeroDB.heroDao().deleteHero(hero)
    }

    suspend fun getListFavouriteHeroes(): List<CharactersHero> {
        return HeroDataBase.HeroDB.heroDao().getFavouriteHeroes()
    }
}
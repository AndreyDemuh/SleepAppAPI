package com.example.sleepappapi.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.sleepappapi.model.CharactersHero

@Dao
interface HeroDao {


    @Insert
    suspend fun insertHero(hero: CharactersHero)

    @Delete
    suspend fun deleteHero(hero: CharactersHero)

    @Query("SELECT * FROM heroes")
    suspend fun getFavouriteHeroes(): List<CharactersHero>

}
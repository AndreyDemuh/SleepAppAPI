package com.example.sleepappapi.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sleepappapi.Hero

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHero(hero: Hero)

    @Delete
    suspend fun deleteHero(hero: Hero)

    @Query("SELECT * FROM local_hero WHERE favourite = 1")
    suspend fun getAllFavouriteHeroes(): List<Hero>
}
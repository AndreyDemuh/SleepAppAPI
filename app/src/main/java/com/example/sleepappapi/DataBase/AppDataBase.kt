package com.example.sleepappapi.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sleepappapi.ConverterType
import com.example.sleepappapi.Hero
import com.example.sleepappapi.InfoHeroConverter
import com.example.sleepappapi.model.CharactersHero

@Database(entities = [CharactersHero::class, Hero::class], version = 1)
@TypeConverters(ConverterType::class, InfoHeroConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun heroDao(): HeroDao
}
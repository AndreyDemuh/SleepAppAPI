package com.example.sleepappapi.DataBase

import android.content.Context
import androidx.room.Room
import javax.inject.Singleton

@Singleton
object HeroDataBase {

    lateinit var HeroDB: AppDataBase

    fun initDB(context: Context) {
        HeroDB = Room.databaseBuilder(
            context,
            AppDataBase::class.java, "database-name"
        ).build()
    }
}
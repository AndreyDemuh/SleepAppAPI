package com.example.sleepappapi

import androidx.room.TypeConverter

class InfoHeroConverter {
    @TypeConverter
    fun fromString(value: String): ArrayList<InfoHero> {
        return ArrayList()
    }

    @TypeConverter
    fun fromListList(list: ArrayList<InfoHero>): String {
        return ""
    }
}
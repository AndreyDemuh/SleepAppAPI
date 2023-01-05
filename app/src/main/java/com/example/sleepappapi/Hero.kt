package com.example.sleepappapi

import androidx.room.*

@Entity(tableName = "local_hero")
data class Hero(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
    @TypeConverters
    @ColumnInfo(name = "listInfo")
    val listInfo: ArrayList<InfoHero>,
    @ColumnInfo(name = "favourite")
    var isFavourite: Boolean
)

data class InfoHero(
    val title: String,
    val list: ArrayList<String>
)
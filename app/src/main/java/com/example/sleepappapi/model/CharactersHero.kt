package com.example.sleepappapi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "heroes")
data class CharactersHero(
    @PrimaryKey
    val _id: Int,
    @ColumnInfo(name = "allies")
    val allies: ArrayList<String>?,
    @ColumnInfo(name = "enemies")
    val enemies: ArrayList<String>?,
    @ColumnInfo(name = "films")
    val films: ArrayList<String>?,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "parkAttractions")
    val parkAttractions: ArrayList<String>?,
    @ColumnInfo(name = "shortFilms")
    val shortFilms: ArrayList<String>?,
    @ColumnInfo(name = "tvShows")
    val tvShows: ArrayList<String>?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "videoGames")
    val videoGames: ArrayList<String>?,
) : Serializable

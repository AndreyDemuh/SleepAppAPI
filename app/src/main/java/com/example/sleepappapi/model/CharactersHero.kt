package com.example.sleepappapi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "heroes")
data class CharactersHero(
    @PrimaryKey
    val _id: String,
    @ColumnInfo(name = "allies")
    val allies: List<String>?,
    @ColumnInfo(name = "enemies")
    val enemies: List<String>?,
    @ColumnInfo(name = "films")
    val films: List<String>?,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "parkAttractions")
    val parkAttractions: List<String>?,
    @ColumnInfo(name = "shortFilms")
    val shortFilms: List<String>?,
    @ColumnInfo(name = "tvShows")
    val tvShows: List<String>?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "videoGames")
    val videoGames: List<String>?,
) : Serializable

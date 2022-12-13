package com.example.sleepappapi

data class Hero(
    val id: Int,
    val name: String?,
    val imageUrl: String?,
    val listInfo: ArrayList<InfoHero>
)

data class InfoHero(
    val title: String,
    val list: ArrayList<String>
)
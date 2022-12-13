package com.example.sleepappapi.utils

import com.example.sleepappapi.Hero
import com.example.sleepappapi.InfoHero
import com.example.sleepappapi.model.CharactersHero

fun CharactersHero.toHero(): Hero {

    val list = arrayListOf<InfoHero>()
    if (allies?.isNotEmpty() == true) {
        list.add(InfoHero("Allies", allies))
    }
    if (enemies?.isNotEmpty() == true) {
        list.add(InfoHero("Enemies", enemies))
    }
    if (films?.isNotEmpty() == true) {
        list.add(InfoHero("Films", films))
    }
    if (parkAttractions?.isNotEmpty() == true) {
        list.add(InfoHero("Park Attractions", parkAttractions))
    }
    if (shortFilms?.isNotEmpty() == true) {
        list.add(InfoHero("Short Films", shortFilms))
    }
    if (tvShows?.isNotEmpty() == true) {
        list.add(InfoHero("TV Shows", tvShows))
    }
    if (videoGames?.isNotEmpty() == true) {
        list.add(InfoHero("Video Games", videoGames))
    }
    return Hero(_id, name, imageUrl, list)
}
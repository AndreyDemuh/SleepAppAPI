package com.example.sleepappapi.ui.allHeroes.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.sleepappapi.model.CharactersHero

class AllHeroUtilCallback : DiffUtil.ItemCallback<CharactersHero>() {
    override fun areItemsTheSame(
        oldItem: CharactersHero, newItem: CharactersHero
    ) : Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(
        oldItem: CharactersHero, newItem: CharactersHero
    ): Boolean {
        return oldItem == newItem
    }
}
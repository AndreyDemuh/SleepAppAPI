package com.example.sleepappapi.ui.adapter.allBase

import androidx.recyclerview.widget.DiffUtil
import com.example.sleepappapi.model.CharactersHero

class AllHeroUtillCallback : DiffUtil.ItemCallback<CharactersHero>() {
    override fun areItemsTheSame(
        oldItem: CharactersHero, newItem: CharactersHero
    ): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(
        oldItem: CharactersHero, newItem: CharactersHero
    ): Boolean {
        return oldItem == newItem
    }
}
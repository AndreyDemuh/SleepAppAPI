package com.example.sleepappapi.ui.adapter.allBase

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sleepappapi.databinding.ItemHeroBinding
import com.example.sleepappapi.model.CharactersHero

class AllHeroesViewHolder(
    private val binding: ItemHeroBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(hero: CharactersHero) {
        binding.nameHero.text = hero.name
        Glide.with(binding.imageHero)
            .load(hero.imageUrl)
            .centerCrop()
            .into(binding.imageHero)
    }
}
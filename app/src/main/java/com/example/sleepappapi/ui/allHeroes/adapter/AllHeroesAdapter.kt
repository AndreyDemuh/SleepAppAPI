package com.example.sleepappapi.ui.allHeroes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.sleepappapi.databinding.ItemHeroBinding
import com.example.sleepappapi.model.CharactersHero

class AllHeroesAdapter(
    private val onClick: (hero: CharactersHero) -> Unit
)  :
    PagingDataAdapter<CharactersHero, AllHeroesViewHolder>(AllHeroUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllHeroesViewHolder {
        return AllHeroesViewHolder(
            ItemHeroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AllHeroesViewHolder, position: Int) {
        getItem(position)?.let { hero ->
            holder.bind(hero)
            holder.itemView.setOnClickListener {
                onClick(hero)
            }
        }
    }
}



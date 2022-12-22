package com.example.sleepappapi.ui.adapter.allBase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.sleepappapi.databinding.ItemHeroBinding
import com.example.sleepappapi.model.CharactersHero

class AllHeroesAdapter(
    private val onClick: (id: CharactersHero) -> Unit
) :
    PagingDataAdapter<CharactersHero, AllHeroesViewHolder>(AllHeroUtillCallback()) {

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
        getItem(position)?.let { CharactersHero ->
            holder.bind(CharactersHero)
            holder.itemView.setOnClickListener {
                onClick(CharactersHero)
            }
        }
    }
}



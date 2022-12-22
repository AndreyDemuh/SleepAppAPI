package com.example.sleepappapi.ui.oneHero.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.sleepappapi.InfoHero
import com.example.sleepappapi.databinding.ItemListInfoBinding

class InfoHeroAdapter : ListAdapter<InfoHero, InfoHeroViewHolder>(InfoHeroDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoHeroViewHolder {
        return InfoHeroViewHolder(
            ItemListInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InfoHeroViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
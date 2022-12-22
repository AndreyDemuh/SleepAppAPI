package com.example.sleepappapi.ui.oneHero.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.sleepappapi.databinding.ItemNameCategoryBinding

class ItemNameAdapter : ListAdapter<String, ItemNameViewHolder>(ItemNameDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNameViewHolder {
        return ItemNameViewHolder(
            ItemNameCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemNameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
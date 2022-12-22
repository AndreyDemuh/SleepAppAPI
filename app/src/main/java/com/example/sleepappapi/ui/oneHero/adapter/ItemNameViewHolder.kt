package com.example.sleepappapi.ui.oneHero.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.sleepappapi.databinding.ItemNameCategoryBinding

class ItemNameViewHolder(
    private val binding: ItemNameCategoryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.itemNameText.text = item
    }
}
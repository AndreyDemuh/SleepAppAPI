package com.example.sleepappapi.ui.oneHero.adapter

import androidx.recyclerview.widget.DiffUtil

class ItemNameDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return false
    }
}
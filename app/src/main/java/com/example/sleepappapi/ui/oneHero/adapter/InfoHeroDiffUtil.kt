package com.example.sleepappapi.ui.oneHero.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.sleepappapi.InfoHero

class InfoHeroDiffUtil : DiffUtil.ItemCallback<InfoHero>() {
    override fun areItemsTheSame(oldItem: InfoHero, newItem: InfoHero): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: InfoHero, newItem: InfoHero): Boolean {
        return false
    }
}
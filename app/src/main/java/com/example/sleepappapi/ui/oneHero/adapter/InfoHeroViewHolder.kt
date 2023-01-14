package com.example.sleepappapi.ui.oneHero.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.sleepappapi.InfoHero
import com.example.sleepappapi.databinding.ItemListInfoBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class InfoHeroViewHolder(
    private val binding: ItemListInfoBinding
) :  RecyclerView.ViewHolder(binding.root) {

    fun bind(item: InfoHero) {
        binding.titleCategory.text = item.title
        binding.infoHeroRV.run {
            if (adapter == null) {
                adapter = ItemNameAdapter()
                layoutManager = FlexboxLayoutManager(binding.root.context).apply {
                    flexDirection = FlexDirection.ROW
                    justifyContent = JustifyContent.FLEX_START
                    flexWrap = FlexWrap.WRAP
                }
            }
            (adapter as? ItemNameAdapter)?.submitList(item.list)
        }
    }
}
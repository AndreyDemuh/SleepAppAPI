package com.example.sleepappapi.ui.allHeroes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sleepappapi.Hero
import com.example.sleepappapi.databinding.ItemHeroBinding

class FavouriteHeroesAdapter(
    private val onClick: (hero: Hero) -> Unit
) :
    RecyclerView.Adapter<FavouriteHeroesAdapter.FavouriteViewHolder>() {

    private var listFavouriteHero = emptyList<Hero>()

    inner class FavouriteViewHolder(val binding: ItemHeroBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        return FavouriteViewHolder(
            ItemHeroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.binding.nameHero.text = listFavouriteHero[position].name
        Glide.with(holder.binding.imageHero)
            .load(listFavouriteHero[position].imageUrl)
            .centerCrop()
            .into(holder.binding.imageHero)

        holder.itemView.setOnClickListener {
            onClick(listFavouriteHero[position])
        }

    }

    override fun getItemCount(): Int {
        return listFavouriteHero.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListFavouriteHero(list: List<Hero>) {
        listFavouriteHero = list
        notifyDataSetChanged()
    }
}
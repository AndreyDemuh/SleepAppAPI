package com.example.sleepappapi

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.sleepappapi.databinding.CustomViewAddHeroBinding

class MessageView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: CustomViewAddHeroBinding

    init {
        binding = CustomViewAddHeroBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setTitle(title: String) {
        binding.message.text = title
    }

    fun setClickClose(onClick: () -> Unit) {
        binding.close.setOnClickListener {
            onClick()
        }
    }
}
package com.example.sleepappapi

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.sleepappapi.databinding.ViewAddHeroBinding

class MessageView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: ViewAddHeroBinding

    init {
        binding = ViewAddHeroBinding.inflate(LayoutInflater.from(context), this, true)
    }
}
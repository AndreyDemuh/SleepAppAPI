package com.example.sleepappapi.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.sleepappapi.databinding.ViewBannerInternetErrorBinding

class BannerView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: ViewBannerInternetErrorBinding

    init {
        binding = ViewBannerInternetErrorBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setTitle(title: String) {
        binding.titleBanner.text = title
    }

    fun setClickOk(onClick: () -> Unit) {
        binding.ok.setOnClickListener {
            this.visibility = View.GONE
            onClick()
        }
    }
}
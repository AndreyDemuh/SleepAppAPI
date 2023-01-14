package com.example.sleepappapi.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val GLOBAL_PREFERENCES = "global_preferences"

@Singleton
class SaveSharedPref @Inject constructor(@ApplicationContext context: Context) {

    private val globalPreferences =
        context.getSharedPreferences(GLOBAL_PREFERENCES, Context.MODE_PRIVATE)

    fun setFavourite(key: String, value: Boolean) {
        globalPreferences.edit().putBoolean(key, value).apply()
    }

    fun getFavourite(key: String): Boolean {
        return globalPreferences.getBoolean(key, false)
    }
}
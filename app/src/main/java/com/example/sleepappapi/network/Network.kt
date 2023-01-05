package com.example.sleepappapi.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.disneyapi.dev"

@Module
@InstallIn(SingletonComponent::class)
class Network {

    @Provides
    fun getCharactersApi(): DisneyHeroApi {
        val retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                )
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(DisneyHeroApi::class.java)
    }
}
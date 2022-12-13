package com.example.sleepappapi.di

import androidx.room.Room
import com.example.sleepappapi.DataBase.AppDataBase
import com.example.sleepappapi.network.DisneyHeroApi
import com.example.sleepappapi.repository.HeroesRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.disneyapi.dev"

val appModule = module {

    single<DisneyHeroApi> {
        val retrofit = Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                    )
                    .build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        retrofit.create(DisneyHeroApi::class.java)
    }

    factory<HeroesRepository> {
        HeroesRepository(api = get())
    }

    single {
        val dataBaseModule = Room.databaseBuilder(
            androidContext(),
            AppDataBase::class.java, "database-name"
        ).build()
        dataBaseModule
    }
}

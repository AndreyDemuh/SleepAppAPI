package com.example.sleepappapi.network

import com.example.sleepappapi.model.AllHeroCategory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DisneyHeroApi {

    @GET("/characters")
    suspend fun getHeroes(
        @Query("page") page: Int,
        @Query("limit") limit: Int? = 100
    ): Response<AllHeroCategory>

    @GET("/characters/{id}")
    suspend fun getImageHero(@Path("id") id: String): Response<AllHeroCategory>

}
package com.example.sleepappapi.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sleepappapi.model.CharactersHero
import javax.inject.Inject

class HeroDataSource @Inject constructor(
    private val repository: HeroesRepository
) : PagingSource<Int, CharactersHero>() {

    override fun getRefreshKey(state: PagingState<Int, CharactersHero>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharactersHero> {
        val key = params.key ?: 1
        val response = repository.getAllHeroes(key, params.loadSize)
        return LoadResult.Page(
            data = response.body()?.data ?: arrayListOf(),
            prevKey = null,
            nextKey = key + 1
        )
    }
}
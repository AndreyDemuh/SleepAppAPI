package com.example.sleepappapi.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sleepappapi.model.CharactersHero
import retrofit2.HttpException
import java.io.IOException
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
        return try {
            val pageNumber = params.key ?: 1
            val response = repository.getAllHeroes(pageNumber, params.loadSize)
            val prevKey = if (pageNumber > 0) pageNumber - 1 else null
            val nextKey = if (response.body()?.data?.isNotEmpty() == true) pageNumber + 1 else null
            return LoadResult.Page(
                data = response.body()?.data ?: arrayListOf(),
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
package com.felippeneves.newmovieapp.core.domain.model

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PagingSourceMovieSearchFactory {
    fun create(movies: List<MovieSearch>) = object : PagingSource<Int, MovieSearch>() {
        override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> =
            LoadResult.Page(
                data = movies,
                prevKey = null,
                nextKey = null
            )
    }
}


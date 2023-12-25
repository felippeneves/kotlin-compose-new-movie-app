package com.felippeneves.newmovieapp.search_movie_feature.domain.source

import com.felippeneves.newmovieapp.core.data.remote.response.MovieSearchResponse
import com.felippeneves.newmovieapp.core.paging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {
    fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource
    suspend fun getSearchMovies(page: Int, query: String): MovieSearchResponse
}
package com.felippeneves.newmovieapp.movie_search_feature.domain.source

import com.felippeneves.newmovieapp.core.domain.model.MovieSearchPaging
import com.felippeneves.newmovieapp.core.paging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {
    fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource
    suspend fun getSearchMovie(page: Int, query: String): MovieSearchPaging
}
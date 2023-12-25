package com.felippeneves.newmovieapp.search_movie_feature.data.source

import com.felippeneves.newmovieapp.core.data.remote.api.MovieService
import com.felippeneves.newmovieapp.core.data.remote.response.MovieSearchResponse
import com.felippeneves.newmovieapp.core.paging.MovieSearchPagingSource
import com.felippeneves.newmovieapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
): MovieSearchRemoteDataSource {
    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource {
        return MovieSearchPagingSource(query = query, remoteDataSource = this)
    }

    override suspend fun getSearchMovies(page: Int, query: String): MovieSearchResponse {
        return service.searchMovie(page = page, query = query)
    }
}
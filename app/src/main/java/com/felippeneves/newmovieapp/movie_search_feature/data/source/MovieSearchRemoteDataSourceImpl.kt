package com.felippeneves.newmovieapp.movie_search_feature.data.source

import com.felippeneves.newmovieapp.core.data.remote.api.MovieService
import com.felippeneves.newmovieapp.core.domain.model.MovieSearchPaging
import com.felippeneves.newmovieapp.core.paging.MovieSearchPagingSource
import com.felippeneves.newmovieapp.movie_search_feature.data.mapper.toMovieSearchPaging
import com.felippeneves.newmovieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieSearchRemoteDataSource {
    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource =
        MovieSearchPagingSource(query = query, remoteDataSource = this)

    override suspend fun getSearchMovie(page: Int, query: String): MovieSearchPaging =
        service.searchMovie(page = page, query = query).toMovieSearchPaging()
}
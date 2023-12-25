package com.felippeneves.newmovieapp.movie_popular_feature.domain.source

import com.felippeneves.newmovieapp.core.data.remote.response.MovieResponse
import com.felippeneves.newmovieapp.core.paging.MoviePagingSource

interface MoviePopularRemoteDataSource {

    fun getPopularMoviesPagingSource(): MoviePagingSource

    suspend fun getPopularMovies(page: Int): MovieResponse
}
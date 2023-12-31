package com.felippeneves.newmovieapp.movie_popular_feature.domain.source

import com.felippeneves.newmovieapp.core.domain.model.MoviePaging
import com.felippeneves.newmovieapp.core.paging.MoviePagingSource

interface MoviePopularRemoteDataSource {

    fun getPopularMoviesPagingSource(): MoviePagingSource

    suspend fun getPopularMovies(page: Int): MoviePaging
}
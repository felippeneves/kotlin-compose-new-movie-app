package com.felippeneves.newmovieapp.movie_details_feature.domain.source

import com.felippeneves.newmovieapp.core.domain.model.MovieDetails
import com.felippeneves.newmovieapp.core.domain.model.MoviePaging
import com.felippeneves.newmovieapp.core.paging.MovieSimilarPagingSource

interface MovieDetailsRemoteDataSource {
    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun getMoviesSimilar(page: Int, movieId: Int): MoviePaging
    fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource
}
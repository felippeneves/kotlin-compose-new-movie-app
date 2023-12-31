package com.felippeneves.newmovieapp.movie_popular_feature.data.source

import com.felippeneves.newmovieapp.core.data.remote.api.MovieService
import com.felippeneves.newmovieapp.core.domain.model.MoviePaging
import com.felippeneves.newmovieapp.core.paging.MoviePagingSource
import com.felippeneves.newmovieapp.movie_popular_feature.data.mapper.toMoviePaging
import com.felippeneves.newmovieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import javax.inject.Inject

class MoviePopularRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MoviePopularRemoteDataSource {
    override fun getPopularMoviesPagingSource(): MoviePagingSource =
        MoviePagingSource(this)

    override suspend fun getPopularMovies(page: Int): MoviePaging =
        service.getPopularMovies(page = page).toMoviePaging()
}
package com.felippeneves.newmovieapp.movie_popular_feature.data.repository

import androidx.paging.PagingSource
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.paging.MoviePagingSource
import com.felippeneves.newmovieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import com.felippeneves.newmovieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource

class MoviePopularRepositoryImpl(
    private val remoteDataSource: MoviePopularRemoteDataSource
) : MoviePopularRepository {
    override fun getPopularMovies(): PagingSource<Int, Movie> =
        MoviePagingSource(remoteDataSource = remoteDataSource)
}
package com.felippeneves.newmovieapp.movie_search_feature.data.repository

import androidx.paging.PagingSource
import com.felippeneves.newmovieapp.core.domain.model.MovieSearch
import com.felippeneves.newmovieapp.core.paging.MovieSearchPagingSource
import com.felippeneves.newmovieapp.movie_search_feature.domain.repository.MovieSearchRepository
import com.felippeneves.newmovieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieSearchRemoteDataSource
) : MovieSearchRepository {
    override fun getSearchMovies(
        query: String,
    ): PagingSource<Int, MovieSearch> =
        MovieSearchPagingSource(
            query = query,
            remoteDataSource = remoteDataSource
        )
}
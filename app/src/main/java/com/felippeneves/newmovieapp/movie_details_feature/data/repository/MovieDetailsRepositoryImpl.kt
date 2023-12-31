package com.felippeneves.newmovieapp.movie_details_feature.data.repository

import androidx.paging.PagingSource
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.domain.model.MovieDetails
import com.felippeneves.newmovieapp.core.paging.MovieSimilarPagingSource
import com.felippeneves.newmovieapp.movie_details_feature.domain.repository.MovieDetailsRepository
import com.felippeneves.newmovieapp.movie_details_feature.domain.source.MovieDetailsRemoteDataSource
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieDetailsRemoteDataSource
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails =
        remoteDataSource.getMovieDetails(movieId = movieId)

    override fun getMoviesSimilar(
        movieId: Int
    ): PagingSource<Int, Movie> =
        MovieSimilarPagingSource(
            movieId = movieId,
            remoteDataSource = remoteDataSource
        )
}
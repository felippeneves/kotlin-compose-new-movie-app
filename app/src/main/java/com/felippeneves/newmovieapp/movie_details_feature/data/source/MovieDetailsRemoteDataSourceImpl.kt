package com.felippeneves.newmovieapp.movie_details_feature.data.source

import com.felippeneves.newmovieapp.core.data.remote.api.MovieService
import com.felippeneves.newmovieapp.core.data.remote.response.MovieResponse
import com.felippeneves.newmovieapp.core.domain.model.MovieDetails
import com.felippeneves.newmovieapp.core.paging.MovieSimilarPagingSource
import com.felippeneves.newmovieapp.core.util.toBackdropUrl
import com.felippeneves.newmovieapp.movie_details_feature.domain.source.MovieDetailsRemoteDataSource
import javax.inject.Inject

class MovieDetailsRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieDetailsRemoteDataSource {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val response = service.getMovie(movieId = movieId)
        val genres = response.genres.map { it.name }
        return MovieDetails(
            id = response.id,
            title = response.title,
            overview = response.overview,
            genres = genres,
            releaseDate = response.releaseDate,
            backdropPathUrl = response.backdropPath.toBackdropUrl(),
            voteAverage = response.voteAverage,
            duration = response.runtime,
            voteCount = response.voteCount
        )
    }

    override suspend fun getMoviesSimilar(page: Int, movieId: Int): MovieResponse =
        service.getMoviesSimilar(page = page, movieId = movieId)

    override fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource =
        MovieSimilarPagingSource(remoteDataSource = this, movieId = movieId)
}
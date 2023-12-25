package com.felippeneves.newmovieapp.movie_favorite_feature.data.repository

import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.source.MovieFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieFavoriteRepositoryImpl @Inject constructor(
    private val localDataSource: MovieFavoriteLocalDataSource
) : MovieFavoriteRepository {
    override fun getMovies(): Flow<List<Movie>> =
        localDataSource.getMovies()

    override suspend fun insert(movie: Movie) =
        localDataSource.insert(movie = movie)

    override suspend fun delete(movie: Movie) =
        localDataSource.delete(movie = movie)

    override suspend fun isFavorite(movieId: Int): Boolean =
        localDataSource.isFavorite(movieId = movieId)
}
package com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase

import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

interface GetMoviesFavoriteUseCase {
    suspend operator fun invoke(): Flow<List<Movie>>
}

class GetMoviesFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieFavoriteRepository
) : GetMoviesFavoriteUseCase {
    override suspend fun invoke(): Flow<List<Movie>> =
        try {
            repository.getMovies()
        } catch (e: Exception) {
            emptyFlow()
        }
}
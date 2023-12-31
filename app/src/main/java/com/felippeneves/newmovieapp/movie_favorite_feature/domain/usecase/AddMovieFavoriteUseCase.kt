package com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase

import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.util.DataResult
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface AddMovieFavoriteUseCase {
    suspend operator fun invoke(params: Params): Flow<DataResult<Unit>>
    data class Params(
        val movie: Movie
    )
}

class AddMovieFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieFavoriteRepository
) : AddMovieFavoriteUseCase {
    override suspend fun invoke(params: AddMovieFavoriteUseCase.Params): Flow<DataResult<Unit>> =
        flow {
            try {
                val result = repository.insert(movie = params.movie)
                emit(DataResult.Success(result))
            } catch (e: Exception) {
                emit(DataResult.Failure(e = e))
            }
        }.flowOn(Dispatchers.IO)
}
package com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase

import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.util.DataResult
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface DeleteMovieFavoriteUseCase {
    suspend operator fun invoke(params: Params): Flow<DataResult<Unit>>
    data class Params(
        val movie: Movie
    )
}

class DeleteMovieFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieFavoriteRepository
) : DeleteMovieFavoriteUseCase {
    override suspend fun invoke(params: DeleteMovieFavoriteUseCase.Params): Flow<DataResult<Unit>> =
        flow {
            try {
                val result = repository.delete(movie = params.movie)
                emit(DataResult.Success(result))
            } catch (e: Exception) {
                emit(DataResult.Failure(e = e))
            }
        }.flowOn(Dispatchers.IO)
}
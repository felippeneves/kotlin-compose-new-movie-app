package com.felippeneves.newmovieapp.movie_details_feature.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.domain.model.MovieDetails
import com.felippeneves.newmovieapp.core.util.DataResult
import com.felippeneves.newmovieapp.movie_details_feature.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface GetMovieDetailsUseCase {
    operator fun invoke(params: Params): Flow<DataResult<Pair<Flow<PagingData<Movie>>, MovieDetails>>>
    data class Params(val movieId: Int)
}

class GetMovieDetailsUseCaseImpl @Inject constructor(
    private val repository: MovieDetailsRepository
) : GetMovieDetailsUseCase {
    override fun invoke(params: GetMovieDetailsUseCase.Params): Flow<DataResult<Pair<Flow<PagingData<Movie>>, MovieDetails>>> =
        flow {
            try {
                emit(DataResult.Loading)
                val movieDetails = repository.getMovieDetails(movieId = params.movieId)
                val moviesSimilar = repository.getMoviesSimilar(
                    movieId = params.movieId,
                    pagingConfig = PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
                //"to" é utilizado para passar 2 parâmetro para o Pair
                emit(DataResult.Success(moviesSimilar to movieDetails))
            } catch (e: HttpException) {
                emit(DataResult.Failure(e))
            } catch (e: IOException) {
                emit(DataResult.Failure(e))
            }
        }.flowOn(Dispatchers.IO) //Garante que as operações sejam executadas em um Thread separada
}
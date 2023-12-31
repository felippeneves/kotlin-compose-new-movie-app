package com.felippeneves.newmovieapp.movie_details_feature.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.domain.model.MovieDetails
import com.felippeneves.newmovieapp.core.util.DataResult
import com.felippeneves.newmovieapp.movie_details_feature.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetMovieDetailsUseCase {
    suspend operator fun invoke(params: Params): DataResult<Pair<Flow<PagingData<Movie>>, MovieDetails>>
    data class Params(val movieId: Int, val pagingConfig: PagingConfig)
}

class GetMovieDetailsUseCaseImpl @Inject constructor(
    private val repository: MovieDetailsRepository
) : GetMovieDetailsUseCase {
    override suspend fun invoke(params: GetMovieDetailsUseCase.Params): DataResult<Pair<Flow<PagingData<Movie>>, MovieDetails>> =
        withContext(Dispatchers.IO) {
            DataResult.Loading
            try {
                val pagingSource = repository.getMoviesSimilar(movieId = params.movieId)
                val movieDetails = repository.getMovieDetails(movieId = params.movieId)
                val pager = Pager(
                    config = params.pagingConfig,
                    pagingSourceFactory = {
                        pagingSource
                    }
                ).flow
                DataResult.Success(data = pager to movieDetails)
            } catch (e: Exception) {
                DataResult.Failure(e = e)
            }
        }
}
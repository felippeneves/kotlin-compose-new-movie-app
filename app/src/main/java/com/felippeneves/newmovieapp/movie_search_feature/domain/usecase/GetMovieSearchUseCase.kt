package com.felippeneves.newmovieapp.movie_search_feature.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.felippeneves.newmovieapp.core.domain.model.MovieSearch
import com.felippeneves.newmovieapp.movie_search_feature.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

interface GetMovieSearchUseCase {
    operator fun invoke(params: Params): Flow<PagingData<MovieSearch>>
    data class Params(val query: String, val pagingConfig: PagingConfig)
}

class GetMovieSearchUseCaseImp @Inject constructor(
    private val repository: MovieSearchRepository
) : GetMovieSearchUseCase {
    override fun invoke(params: GetMovieSearchUseCase.Params): Flow<PagingData<MovieSearch>> =
        try {
            val pagingSource = repository.getSearchMovies(query = params.query)

            Pager(
                config = params.pagingConfig,
                pagingSourceFactory = {
                    pagingSource
                }
            ).flow
        } catch (e: Exception) {
            emptyFlow()
        }
}
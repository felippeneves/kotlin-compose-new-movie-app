package com.felippeneves.newmovieapp.movie_popular_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import com.felippeneves.newmovieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MoviePopularRepositoryImpl(
    private val remoteDataSource: MoviePopularRemoteDataSource
) : MoviePopularRepository {
    override fun getPopularMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>> {
        return Pager(
            //Configurações de paginação
            config = pagingConfig,
            pagingSourceFactory = {
                //Responsável por carregar os dados da API
                remoteDataSource.getPopularMoviesPagingSource()
            }
        ).flow
    }
}
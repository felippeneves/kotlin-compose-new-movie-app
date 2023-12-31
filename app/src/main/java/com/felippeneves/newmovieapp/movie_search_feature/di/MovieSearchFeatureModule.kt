package com.felippeneves.newmovieapp.movie_search_feature.di

import com.felippeneves.newmovieapp.core.data.remote.api.MovieService
import com.felippeneves.newmovieapp.movie_search_feature.data.repository.MovieSearchRepositoryImpl
import com.felippeneves.newmovieapp.movie_search_feature.data.source.MovieSearchRemoteDataSourceImpl
import com.felippeneves.newmovieapp.movie_search_feature.domain.repository.MovieSearchRepository
import com.felippeneves.newmovieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource
import com.felippeneves.newmovieapp.movie_search_feature.domain.usecase.GetMovieSearchUseCase
import com.felippeneves.newmovieapp.movie_search_feature.domain.usecase.GetMovieSearchUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieSearchFeatureModule {
    @Provides
    @Singleton
    fun provideMovieSearchDataSource(service: MovieService): MovieSearchRemoteDataSource =
        MovieSearchRemoteDataSourceImpl(service = service)

    @Provides
    @Singleton
    fun provideMovieSearchRepository(remoteDataSource: MovieSearchRemoteDataSource): MovieSearchRepository =
        MovieSearchRepositoryImpl(remoteDataSource = remoteDataSource)

    @Provides
    @Singleton
    fun provideGetMovieSearchUseCase(repository: MovieSearchRepository): GetMovieSearchUseCase =
        GetMovieSearchUseCaseImp(repository = repository)
}
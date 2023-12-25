package com.felippeneves.newmovieapp.movie_popular_feature.di

import com.felippeneves.newmovieapp.core.data.remote.api.MovieService
import com.felippeneves.newmovieapp.movie_popular_feature.data.repository.MoviePopularRepositoryImpl
import com.felippeneves.newmovieapp.movie_popular_feature.data.source.MoviePopularRemoteDataSourceImpl
import com.felippeneves.newmovieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import com.felippeneves.newmovieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import com.felippeneves.newmovieapp.movie_popular_feature.domain.usecase.GetPopularMoviesUseCase
import com.felippeneves.newmovieapp.movie_popular_feature.domain.usecase.GetPopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviePopularFeatureModule {
    @Provides
    @Singleton
    fun provideMovieDataSource(service: MovieService): MoviePopularRemoteDataSource =
        MoviePopularRemoteDataSourceImpl(service = service)

    @Provides
    @Singleton
    fun provideMovieRepository(remoteDataSource: MoviePopularRemoteDataSource): MoviePopularRepository =
        MoviePopularRepositoryImpl(remoteDataSource = remoteDataSource)

    @Provides
    @Singleton
    fun provideGetMoviesPopularUseCase(repository: MoviePopularRepository): GetPopularMoviesUseCase =
        GetPopularMoviesUseCaseImpl(repository = repository)
}
package com.felippeneves.newmovieapp.movie_details_feature.di

import com.felippeneves.newmovieapp.core.data.remote.api.MovieService
import com.felippeneves.newmovieapp.movie_details_feature.data.repository.MovieDetailsRepositoryImpl
import com.felippeneves.newmovieapp.movie_details_feature.data.source.MovieDetailsRemoteDataSourceImpl
import com.felippeneves.newmovieapp.movie_details_feature.domain.repository.MovieDetailsRepository
import com.felippeneves.newmovieapp.movie_details_feature.domain.source.MovieDetailsRemoteDataSource
import com.felippeneves.newmovieapp.movie_details_feature.domain.usecase.GetMovieDetailsUseCase
import com.felippeneves.newmovieapp.movie_details_feature.domain.usecase.GetMovieDetailsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsModule {

    @Provides
    @Singleton
    fun provideMovieDetailsDataSource(service: MovieService): MovieDetailsRemoteDataSource =
        MovieDetailsRemoteDataSourceImpl(service = service)

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(remoteDataSource: MovieDetailsRemoteDataSource): MovieDetailsRepository =
        MovieDetailsRepositoryImpl(remoteDataSource = remoteDataSource)

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(repository: MovieDetailsRepository): GetMovieDetailsUseCase =
        GetMovieDetailsUseCaseImpl(repository = repository)
}
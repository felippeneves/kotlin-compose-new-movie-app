package com.felippeneves.newmovieapp.movie_favorite_feature.di

import com.felippeneves.newmovieapp.core.data.local.dao.MovieDao
import com.felippeneves.newmovieapp.movie_favorite_feature.data.repository.MovieFavoriteRepositoryImpl
import com.felippeneves.newmovieapp.movie_favorite_feature.data.source.MovieFavoriteLocalDataSourceImpl
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.source.MovieFavoriteLocalDataSource
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCaseImpl
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCaseImpl
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCase
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCaseImpl
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieFavoriteModule {
    @Provides
    @Singleton
    fun provideMovieFavoriteLocalDataSource(dao: MovieDao): MovieFavoriteLocalDataSource =
        MovieFavoriteLocalDataSourceImpl(dao = dao)

    @Provides
    @Singleton
    fun provideMovieFavoriteRepository(localDataSource: MovieFavoriteLocalDataSource): MovieFavoriteRepository =
        MovieFavoriteRepositoryImpl(localDataSource = localDataSource)

    @Provides
    @Singleton
    fun provideAddMovieFavoriteUseCase(repository: MovieFavoriteRepository): AddMovieFavoriteUseCase =
        AddMovieFavoriteUseCaseImpl(repository = repository)

    @Provides
    @Singleton
    fun provideDeleteMovieFavoriteUseCase(repository: MovieFavoriteRepository): DeleteMovieFavoriteUseCase =
        DeleteMovieFavoriteUseCaseImpl(repository = repository)

    @Provides
    @Singleton
    fun provideGetMoviesFavoriteUseCase(repository: MovieFavoriteRepository): GetMoviesFavoriteUseCase =
        GetMoviesFavoriteUseCaseImpl(repository = repository)

    @Provides
    @Singleton
    fun provideIsMovieFavoriteUseCase(repository: MovieFavoriteRepository): IsMovieFavoriteUseCase =
        IsMovieFavoriteUseCaseImpl(repository = repository)
}
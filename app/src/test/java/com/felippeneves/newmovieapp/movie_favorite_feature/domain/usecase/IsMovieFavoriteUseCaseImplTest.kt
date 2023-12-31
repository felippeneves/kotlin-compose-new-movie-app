package com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase

import com.felippeneves.newmovieapp.TestDispatcherRule
import com.felippeneves.newmovieapp.core.domain.model.MovieFactory
import com.felippeneves.newmovieapp.core.util.DataResult
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IsMovieFavoriteUseCaseImplTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.CaptainAmerica)

    //O que vai ser testado NUNCA é mockado
    private val isMovieFavoriteUseCase by lazy {
        IsMovieFavoriteUseCaseImpl(repository = movieFavoriteRepository)
    }

    @Test
    fun `must return Success from ResultStatus when the repository returns success with the value equal to true`() =
        runTest {
            //Given
            whenever(movieFavoriteRepository.isFavorite(any())).thenReturn(true)

            //When
            val result = isMovieFavoriteUseCase.invoke(
                params = IsMovieFavoriteUseCase.Params(movie.id)
            ).first()

            //Then
            Truth.assertThat(result).isEqualTo(DataResult.Success(data = true))
        }

    @Test
    fun `must return Success from ResultStatus when the repository returns success with the value equal to false`() =
        runTest {
            //Given
            whenever(movieFavoriteRepository.isFavorite(any())).thenReturn(false)

            //When
            val result = isMovieFavoriteUseCase.invoke(
                params = IsMovieFavoriteUseCase.Params(movie.id)
            ).first()

            //Then
            Truth.assertThat(result).isEqualTo(DataResult.Success(data = false))
        }

    @Test
    fun `must return Failure from ResultStatus when the repository throws an exception`() =
        runTest {
            //Given
            val exception = RuntimeException()
            whenever(movieFavoriteRepository.isFavorite(any())).thenThrow(exception)

            //When
            val result = isMovieFavoriteUseCase.invoke(
                params = IsMovieFavoriteUseCase.Params(movie.id)
            ).first()

            //Then
            Truth.assertThat(result).isEqualTo(DataResult.Failure(e = exception))
        }
}
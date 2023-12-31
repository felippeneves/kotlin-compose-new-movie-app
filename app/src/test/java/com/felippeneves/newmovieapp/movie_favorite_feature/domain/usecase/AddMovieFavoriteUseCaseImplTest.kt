package com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase

import com.felippeneves.newmovieapp.TestDispatcherRule
import com.felippeneves.newmovieapp.core.domain.model.MovieFactory
import com.felippeneves.newmovieapp.core.util.DataResult
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddMovieFavoriteUseCaseImplTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.CaptainAmerica)

    //O que vai ser testado NUNCA é mockado
    private val addMovieFavoriteUseCase by lazy {
        AddMovieFavoriteUseCaseImpl(repository = movieFavoriteRepository)
    }

    @Test
    fun `must return Success from ResultStatus when the repository returns success equal to unit`() =
        runTest {
            //Given
            whenever(movieFavoriteRepository.insert(movie)).thenReturn(Unit)

            //When
            val result = addMovieFavoriteUseCase.invoke(
                params = AddMovieFavoriteUseCase.Params(
                    movie = movie
                )
            ).first()

            //Then
            Truth.assertThat(result).isEqualTo(DataResult.Success(Unit))
        }

    @Test
    fun `must return Failure from ResultStatus when the repository throws an exception`() =
        runTest {
            //Given
            val exception = RuntimeException()
            whenever(movieFavoriteRepository.insert(movie)).thenThrow(exception)

            //When
            val result = addMovieFavoriteUseCase.invoke(
                params = AddMovieFavoriteUseCase.Params(
                    movie = movie
                )
            ).first()

            //Then
            Truth.assertThat(result).isEqualTo(DataResult.Failure(e = exception))
        }
}
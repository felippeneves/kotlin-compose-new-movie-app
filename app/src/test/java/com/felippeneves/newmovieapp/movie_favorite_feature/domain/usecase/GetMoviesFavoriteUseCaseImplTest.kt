package com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase

import com.felippeneves.newmovieapp.TestDispatcherRule
import com.felippeneves.newmovieapp.core.domain.model.MovieFactory
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMoviesFavoriteUseCaseImplTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movies = listOf(
        MovieFactory().create(poster = MovieFactory.Poster.CaptainAmerica),
        MovieFactory().create(poster = MovieFactory.Poster.Avengers)
    )

    //O que vai ser testado NUNCA é mockado
    private val getMoviesFavoriteUseCase by lazy {
        GetMoviesFavoriteUseCaseImpl(repository = movieFavoriteRepository)
    }

    @Test
    fun `must return Success from ResultStatus when the repository returns a list of movies`() =
        runTest {
            //Given
            whenever(movieFavoriteRepository.getMovies()).thenReturn(
                flowOf(movies)
            )

            //When
            val result = getMoviesFavoriteUseCase.invoke().first()

            //Then
            Truth.assertThat(result).isNotEmpty()
            Truth.assertThat(result).contains(movies[1])
        }

    @Test
    fun `must emit an empty stream when exception is thrown when calling the invoke method`() =
        runTest {
            //Given
            val exception = RuntimeException()
            whenever(movieFavoriteRepository.getMovies()).thenThrow(exception)

            //When
            //Como é emitido um fluxo vazio é melhor usar o toList()
            val result = getMoviesFavoriteUseCase.invoke().toList()

            //Then
            verify(movieFavoriteRepository).getMovies() //Verifica se foi chamado
            Truth.assertThat(result).isEmpty()
        }
}
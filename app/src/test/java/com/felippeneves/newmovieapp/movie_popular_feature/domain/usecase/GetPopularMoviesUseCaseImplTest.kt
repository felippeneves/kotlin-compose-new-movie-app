package com.felippeneves.newmovieapp.movie_popular_feature.domain.usecase

import com.felippeneves.newmovieapp.TestDispatcherRule
import com.felippeneves.newmovieapp.core.domain.model.MovieFactory
import com.felippeneves.newmovieapp.core.domain.model.PagingSourceMoviesFactory
import com.felippeneves.newmovieapp.core.util.getPagingConfig
import com.felippeneves.newmovieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesUseCaseImplTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var moviePopularRepository: MoviePopularRepository

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.CaptainAmerica)

    private val pagingSourceFake = PagingSourceMoviesFactory().create(movies = listOf(movie))

    //O que vai ser testado NUNCA é mockado
    private val getPopularMoviesUseCase by lazy {
        GetPopularMoviesUseCaseImpl(repository = moviePopularRepository)
    }

    @Test
    fun `must validate flow paging data creation when invoke from use case is called`() =
        runTest {
            //Given
            whenever(moviePopularRepository.getPopularMovies())
                .thenReturn(pagingSourceFake)

            //When
            val result = getPopularMoviesUseCase.invoke(
                params = GetPopularMoviesUseCase.Params(
                    pagingConfig = getPagingConfig()
                )
            ).first()

            //Then
            verify(moviePopularRepository).getPopularMovies()
            Truth.assertThat(result).isNotNull() //Verifica se o fluxo foi preenchido
        }

    @Test
    fun `must emit an empty stream when an exception is thrown when calling the invoke method`() =
        runTest {
            //Given
            val exception = RuntimeException()
            whenever(moviePopularRepository.getPopularMovies())
                .thenThrow(exception)

            //When
            val result = getPopularMoviesUseCase.invoke(
                params = GetPopularMoviesUseCase.Params(
                    pagingConfig = getPagingConfig()
                )
            )

            //Then
            val resultList = result.toList()
            verify(moviePopularRepository).getPopularMovies()
            Truth.assertThat(resultList).isEmpty()
        }
}
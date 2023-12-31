package com.felippeneves.newmovieapp.movie_search_feature.domain.usecase

import com.felippeneves.newmovieapp.TestDispatcherRule
import com.felippeneves.newmovieapp.core.domain.model.MovieSearchFactory
import com.felippeneves.newmovieapp.core.domain.model.PagingSourceMovieSearchFactory
import com.felippeneves.newmovieapp.core.util.getPagingConfig
import com.felippeneves.newmovieapp.movie_search_feature.domain.repository.MovieSearchRepository
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
class GetMovieSearchUseCaseImpTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieSearchRepository: MovieSearchRepository

    private val movieSearchFactory =
        MovieSearchFactory().create(poster = MovieSearchFactory.Poster.CaptainAmerica)

    private val pagingSourceFake = PagingSourceMovieSearchFactory().create(
        movies = listOf(movieSearchFactory)
    )

    //O que vai ser testado NUNCA é mockado
    private val getMovieSearchUseCase by lazy {
        GetMovieSearchUseCaseImp(repository = movieSearchRepository)
    }

    @Test
    fun `must validate flow paging data creation when invoke from use case is called`() =
        runTest {
            //Given
            whenever(movieSearchRepository.getSearchMovies(query = ""))
                .thenReturn(pagingSourceFake)

            //When
            val result = getMovieSearchUseCase.invoke(
                params = GetMovieSearchUseCase.Params(
                    query = "",
                    pagingConfig = getPagingConfig()
                )
            ).first()

            //Then
            verify(movieSearchRepository).getSearchMovies(query = "")
            Truth.assertThat(result).isNotNull() //Verifica se o fluxo foi preenchido
        }

    @Test
    fun `must emit an empty stream when an exception is thrown when calling the invoke method`() =
        runTest {
            //Given
            val exception = RuntimeException()
            whenever(movieSearchRepository.getSearchMovies(query = ""))
                .thenThrow(exception)

            //When
            val result = getMovieSearchUseCase.invoke(
                params = GetMovieSearchUseCase.Params(
                    query = "",
                    pagingConfig = getPagingConfig()
                )
            ).toList()

            //Then
            verify(movieSearchRepository).getSearchMovies(query = "")
            Truth.assertThat(result).isEmpty()
        }
}
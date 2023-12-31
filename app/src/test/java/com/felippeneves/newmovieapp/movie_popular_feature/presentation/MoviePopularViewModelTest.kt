package com.felippeneves.newmovieapp.movie_popular_feature.presentation

import androidx.paging.PagingData
import com.felippeneves.newmovieapp.TestDispatcherRule
import com.felippeneves.newmovieapp.core.domain.model.MovieFactory
import com.felippeneves.newmovieapp.movie_popular_feature.domain.usecase.GetPopularMoviesUseCase
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviePopularViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    //Cria uma instância vazia
    @Mock
    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    //Com o lazy, é inicializado o atributo apenas quando ela for utilizada
    private val viewModel by lazy {
        MoviePopularViewModel(getPopularMoviesUseCase = getPopularMoviesUseCase)
    }

    private val fakePagingDataMovies = PagingData.from(
        listOf(
            MovieFactory().create(poster = MovieFactory.Poster.CaptainAmerica),
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
            MovieFactory().create(poster = MovieFactory.Poster.SpiderMan),
        )
    )

    @Test
    fun `must validate paging data object values when calling paging date from movies`() = runTest {
        //Given
        whenever(getPopularMoviesUseCase.invoke(any()))
            .thenReturn(flowOf(fakePagingDataMovies))

        //When
        val result = viewModel.uiState.movies.first()

        //Then
        assertThat(result).isNotNull()
    }

    @Test(expected = RuntimeException::class)
    fun `must throw an exception when the calling to the use case returns exception`() = runTest {

        //Given
        whenever(getPopularMoviesUseCase.invoke(any()))
            .thenThrow(RuntimeException())

        //When
        viewModel.uiState.movies.first()

    }
}
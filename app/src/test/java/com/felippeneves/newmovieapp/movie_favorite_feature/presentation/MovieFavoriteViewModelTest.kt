package com.felippeneves.newmovieapp.movie_favorite_feature.presentation

import com.felippeneves.newmovieapp.TestDispatcherRule
import com.felippeneves.newmovieapp.core.domain.model.MovieFactory
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCase
import com.google.common.truth.Truth.assertThat
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
class MovieFavoriteViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    //Cria uma instância vazia
    @Mock
    lateinit var getMoviesFavoriteUseCase: GetMoviesFavoriteUseCase

    private val viewModel by lazy {
        MovieFavoriteViewModel(getMoviesFavoriteUseCase = getMoviesFavoriteUseCase)
    }

    private val moviesFavorite = listOf(
        MovieFactory().create(poster = MovieFactory.Poster.CaptainAmerica),
        MovieFactory().create(poster = MovieFactory.Poster.Avengers),
        MovieFactory().create(poster = MovieFactory.Poster.SpiderMan),
    )

    @Test
    fun `must validate the data object values when calling list of favorites`() = runTest {
        //Given
        whenever(getMoviesFavoriteUseCase.invoke()).thenReturn(
            flowOf(moviesFavorite)
        )

        //When
        val result = viewModel.uiState.movies.first()

        //Then
        assertThat(result).isNotEmpty()
        assertThat(result).contains(moviesFavorite[0])
    }

    @Test(expected = RuntimeException::class)
    fun `must throw an exception when the calling to the use case returns exception`() = runTest {

        //Given
        whenever(getMoviesFavoriteUseCase.invoke())
            .thenThrow(RuntimeException())

        //When
        viewModel.uiState.movies.first()
    }
}
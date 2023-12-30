package com.felippeneves.newmovieapp.movie_details_feature.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import com.felippeneves.newmovieapp.TestDispatcherRule
import com.felippeneves.newmovieapp.core.domain.model.MovieDetailsFactory
import com.felippeneves.newmovieapp.core.domain.model.MovieFactory
import com.felippeneves.newmovieapp.core.util.Constants
import com.felippeneves.newmovieapp.core.util.DataResult
import com.felippeneves.newmovieapp.movie_details_feature.domain.usecase.GetMovieDetailsUseCase
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailsViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = TestDispatcherRule()


    @Mock
    lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    @Mock
    lateinit var addMovieFavoriteUseCase: AddMovieFavoriteUseCase

    @Mock
    lateinit var deleteMovieFavoriteUseCase: DeleteMovieFavoriteUseCase

    @Mock
    lateinit var isMovieFavoriteUseCase: IsMovieFavoriteUseCase

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    private val movieDetailsFactory =
        MovieDetailsFactory().create(poster = MovieDetailsFactory.Poster.GuardiansOfTheGalaxy)

    private val movie =
        MovieFactory().create(poster = MovieFactory.Poster.CaptainAmerica)

    private val fakePagingDataMovies = PagingData.from(
        listOf(
            MovieFactory().create(poster = MovieFactory.Poster.CaptainAmerica),
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
            MovieFactory().create(poster = MovieFactory.Poster.SpiderMan),
        )
    )

    //Com o lazy, é inicializado o atributo apenas quando ela for utilizada
    private val viewModel by lazy {
        MovieDetailsViewModel(
            getMovieDetailsUseCase = getMovieDetailsUseCase,
            addMovieFavoriteUseCase = addMovieFavoriteUseCase,
            deleteMovieFavoriteUseCase = deleteMovieFavoriteUseCase,
            isMovieFavoriteUseCase = isMovieFavoriteUseCase,
            //Faz o mock do atributo que é passado como parâmetro para a tela do view model que está sendo testado
            // e é utilizado para carregar os detalhes do filme
            savedStateHandle = savedStateHandle.apply {
                whenever(savedStateHandle.get<Int>(Constants.MOVIE_DETAIL_ARGUMENT_KEY)).thenReturn(
                    movie.id
                )
            }
        )
    }

    @Test
    fun `must notify uiState with Success when get movies similar and movie details returns success`() =
        runTest {
            //Given
            whenever(getMovieDetailsUseCase.invoke(any()))
                .thenReturn(flowOf(DataResult.Success(flowOf(fakePagingDataMovies) to movieDetailsFactory)))

            //Recupera o parâmetro o movie Id que é passado no mock do savedStateHandle
            val argumentCaptor = argumentCaptor<GetMovieDetailsUseCase.Params>()

            //When
            viewModel.uiState.isLoading //Força a inicialização do view model

            //Then
            //Verifica se o método invoke do use case que está sendo testado foi chamado durante a execução do teste
            verify(getMovieDetailsUseCase).invoke(argumentCaptor.capture())
            assertThat(movieDetailsFactory.id).isEqualTo(argumentCaptor.firstValue.movieId)
            val movieDetails = viewModel.uiState.movieDetails
            val results = viewModel.uiState.results
            assertThat(movieDetails).isNotNull()
            assertThat(movieDetails?.id).isEqualTo(movieDetailsFactory.id)
            assertThat(results).isNotNull()
        }

    @Test
    fun `must notify uiState with Failure when get movies details and returns exception`() =
        runTest {
            //Given
            val exception = Exception("An error has occurred!")
            whenever(getMovieDetailsUseCase.invoke(any()))
                .thenReturn(flowOf(DataResult.Failure(exception)))

            //When
            viewModel.uiState.isLoading //Força a inicialização do view model

            //Then
            val error = viewModel.uiState.error
            assertThat(exception.message).isEqualTo(error)
        }
}
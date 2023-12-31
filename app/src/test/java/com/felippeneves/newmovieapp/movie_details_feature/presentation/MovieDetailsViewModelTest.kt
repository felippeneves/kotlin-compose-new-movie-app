package com.felippeneves.newmovieapp.movie_details_feature.presentation

import androidx.compose.ui.graphics.Color
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
                .thenReturn(DataResult.Success(data = flowOf(fakePagingDataMovies) to movieDetailsFactory))

            whenever(isMovieFavoriteUseCase.invoke(any()))
                .thenReturn(flowOf(DataResult.Success(data = true)))

            //Recupera o parâmetro o movie Id que é passado no mock do savedStateHandle
            val argumentCaptor = argumentCaptor<GetMovieDetailsUseCase.Params>()
            val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

            //When
            viewModel.uiState.isLoading //Força a inicialização do view model

            //Then
            verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
            assertThat(movieDetailsFactory.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

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
                .thenReturn(DataResult.Failure(e = exception))

            whenever(isMovieFavoriteUseCase.invoke(any()))
                .thenReturn(flowOf(DataResult.Failure(e = exception)))

            //When
            viewModel.uiState.isLoading //Força a inicialização do view model

            //Then
            val error = viewModel.uiState.error
            assertThat(exception.message).isEqualTo(error)
        }

    @Test
    fun `must call delete favorite and notify of uiState with filled favorite icon when current icon is unchecked`() =
        runTest {
            //Given
            whenever(deleteMovieFavoriteUseCase.invoke(any()))
                .thenReturn(flowOf(DataResult.Success(Unit)))

            whenever(isMovieFavoriteUseCase.invoke(any()))
                .thenReturn(flowOf(DataResult.Success(data = true)))

            val deleteArgumentsCaptor = argumentCaptor<DeleteMovieFavoriteUseCase.Params>()
            val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

            //When
            viewModel.onHandleFavorite(movie = movie)

            //Then
            verify(deleteMovieFavoriteUseCase).invoke(deleteArgumentsCaptor.capture())
            assertThat(movie).isEqualTo(deleteArgumentsCaptor.firstValue.movie)

            verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
            assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

            val iconColor = viewModel.uiState.iconColor
            assertThat(Color.White).isEqualTo(iconColor) //Cor do ícone quando o filme não é mais favorito
        }

    @Test
    fun `must notify uiState with filled favorite icon when current icon is checked`() =
        runTest {
            //Given
            whenever(addMovieFavoriteUseCase.invoke(any()))
                .thenReturn(flowOf(DataResult.Success(Unit)))

            whenever(isMovieFavoriteUseCase.invoke(any()))
                .thenReturn(flowOf(DataResult.Success(data = false)))

            val addArgumentsCaptor = argumentCaptor<AddMovieFavoriteUseCase.Params>()
            val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

            //When
            viewModel.onHandleFavorite(movie = movie)

            //Then
            verify(addMovieFavoriteUseCase).invoke(addArgumentsCaptor.capture())
            assertThat(movie).isEqualTo(addArgumentsCaptor.firstValue.movie)

            verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
            assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

            val iconColor = viewModel.uiState.iconColor
            assertThat(Color.Red).isEqualTo(iconColor) //Cor do ícone quando o filme é favorito
        }

    @Test
    fun `must notify uiState with bookmark icon filled in if bookmark check returns true`() =
        runTest {
            //Given
            whenever(isMovieFavoriteUseCase.invoke(any()))
                .thenReturn(flowOf(DataResult.Success(data = true)))

            whenever(getMovieDetailsUseCase.invoke(any()))
                .thenReturn(DataResult.Success(data = flowOf(fakePagingDataMovies) to movieDetailsFactory))

            val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

            //When
            viewModel.uiState.isLoading

            verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
            assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

            val iconColor = viewModel.uiState.iconColor
            assertThat(Color.Red).isEqualTo(iconColor)
        }

    @Test
    fun `must notify uiState with bookmark icon filled in if bookmark check returns false`() =
        runTest {
            //Given
            whenever(isMovieFavoriteUseCase.invoke(any()))
                .thenReturn(flowOf(DataResult.Success(data = false)))

            val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

            //When
            viewModel.uiState.isLoading

            verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
            assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

            val iconColor = viewModel.uiState.iconColor
            assertThat(Color.White).isEqualTo(iconColor)
        }
}
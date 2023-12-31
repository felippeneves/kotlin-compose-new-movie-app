package com.felippeneves.newmovieapp.movie_details_feature.domain.usecase

import com.felippeneves.newmovieapp.TestDispatcherRule
import com.felippeneves.newmovieapp.core.domain.model.MovieDetailsFactory
import com.felippeneves.newmovieapp.core.domain.model.MovieFactory
import com.felippeneves.newmovieapp.core.domain.model.PagingSourceMoviesFactory
import com.felippeneves.newmovieapp.core.util.DataResult
import com.felippeneves.newmovieapp.core.util.getPagingConfig
import com.felippeneves.newmovieapp.movie_details_feature.domain.repository.MovieDetailsRepository
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailsUseCaseImplTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieDetailsRepository: MovieDetailsRepository

    private val movieFactory = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val movieDetailsFactory =
        MovieDetailsFactory().create(poster = MovieDetailsFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesFactory().create(
        movies = listOf(movieFactory)
    )

    //O que vai ser testado NUNCA é mockado
    private val getMovieDetailsUseCase by lazy {
        GetMovieDetailsUseCaseImpl(repository = movieDetailsRepository)
    }

    @Test
    fun `must return Success from ResultStatus when get both requests return success`() =
        runTest {
            //Given
            whenever(movieDetailsRepository.getMovieDetails(movieId = movieFactory.id))
                .thenReturn(movieDetailsFactory)
            whenever(movieDetailsRepository.getMoviesSimilar(movieId = movieFactory.id))
                .thenReturn(pagingSourceFake)

            //When
            val result = getMovieDetailsUseCase.invoke(
                params = GetMovieDetailsUseCase.Params(
                    movieId = movieFactory.id,
                    pagingConfig = getPagingConfig()
                )
            )

            //Then
            verify(movieDetailsRepository).getMovieDetails(movieId = movieFactory.id)
            verify(movieDetailsRepository).getMoviesSimilar(movieId = movieFactory.id)

            Truth.assertThat(result).isNotNull()
            Truth.assertThat(result is DataResult.Success).isTrue()
        }

    @Test
    fun `must return Error from ResultStatus when get details of the movie request returns error`() =
        runTest {
            //Given
            val exception = RuntimeException()
            whenever(movieDetailsRepository.getMovieDetails(movieId = movieFactory.id))
                .thenThrow(exception)

            //When
            val result = getMovieDetailsUseCase.invoke(
                params = GetMovieDetailsUseCase.Params(
                    movieId = movieFactory.id,
                    pagingConfig = getPagingConfig()
                )
            )

            //Then
            verify(movieDetailsRepository).getMovieDetails(movieId = movieFactory.id)
            Truth.assertThat(result is DataResult.Failure).isTrue()
        }

    @Test
    fun `must return Error from ResultStatus when getting similar movies request returns error`() =
        runTest {
            //Given
            val exception = RuntimeException()
            whenever(movieDetailsRepository.getMoviesSimilar(movieId = movieFactory.id))
                .thenThrow(exception)

            //When
            val result = getMovieDetailsUseCase.invoke(
                params = GetMovieDetailsUseCase.Params(
                    movieId = movieFactory.id,
                    pagingConfig = getPagingConfig()
                )
            )

            //Then
            verify(movieDetailsRepository).getMoviesSimilar(movieId = movieFactory.id)
            Truth.assertThat(result is DataResult.Failure).isTrue()
        }
}
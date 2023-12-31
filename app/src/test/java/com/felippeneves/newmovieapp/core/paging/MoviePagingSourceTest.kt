package com.felippeneves.newmovieapp.core.paging

import androidx.paging.PagingSource
import com.felippeneves.newmovieapp.TestDispatcherRule
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.domain.model.MovieFactory
import com.felippeneves.newmovieapp.core.domain.model.MoviePagingFactory
import com.felippeneves.newmovieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviePagingSourceTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: MoviePopularRemoteDataSource

    private val movieFactory = MovieFactory()

    private val moviePagingSource by lazy {
        MoviePagingSource(remoteDataSource = remoteDataSource)
    }

    private val moviePagingFactory = MoviePagingFactory().create()

    @Test
    fun `must return a success load result when load is called`() = runTest {
        //Given
        whenever(remoteDataSource.getPopularMovies(any()))
            .thenReturn(moviePagingFactory)

        //When
        val result = moviePagingSource.load(
            //É chamado sempre que é feito uma requisição da primeira página
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = moviePagingFactory.totalResults,
                placeholdersEnabled = false
            )
        )

        val resultExpected = listOf(
            movieFactory.create(MovieFactory.Poster.CaptainAmerica),
            movieFactory.create(MovieFactory.Poster.Avengers),
        )

        //Then
        assertThat(
            PagingSource.LoadResult.Page(
                data = resultExpected,
                //O teste é realizado baseado em apenas uma página
                prevKey = null,
                nextKey = null
            )
        ).isEqualTo(result)
    }

    @Test
    fun `must return an error load result when load is called`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(remoteDataSource.getPopularMovies(any()))
            .thenThrow(exception)

        //When
        val result = moviePagingSource.load(
            //É chamado sempre que é feito uma requisição da primeira página
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = moviePagingFactory.totalResults,
                placeholdersEnabled = false
            )
        )

        //Then
        assertThat(PagingSource.LoadResult.Error<Int, Movie>(exception)).isEqualTo(result)
    }
}
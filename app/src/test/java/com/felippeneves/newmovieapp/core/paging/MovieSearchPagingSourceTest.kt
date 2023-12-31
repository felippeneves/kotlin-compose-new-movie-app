package com.felippeneves.newmovieapp.core.paging

import androidx.paging.PagingSource
import com.felippeneves.newmovieapp.TestDispatcherRule
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.domain.model.MovieSearchFactory
import com.felippeneves.newmovieapp.core.domain.model.MovieSearchPagingFactory
import com.felippeneves.newmovieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource
import com.google.common.truth.Truth
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
class MovieSearchPagingSourceTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: MovieSearchRemoteDataSource

    private val moviesSearchFactory = MovieSearchFactory()

    private val moviesSearchPagingFactory = MovieSearchPagingFactory().create()

    //Quem é testado nunca é passado a anotação @Mock
    private val movieSearchPagingSource by lazy {
        MovieSearchPagingSource(
            query = "",
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun `must return a success load result when load is called`() = runTest {
        //Given
        whenever(remoteDataSource.getSearchMovie(any(), any()))
            .thenReturn(moviesSearchPagingFactory)

        //When
        val result = movieSearchPagingSource.load(
            //É chamado sempre que é feito uma requisição da primeira página
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = moviesSearchPagingFactory.totalResults,
                placeholdersEnabled = false
            )
        )

        val resultExpected = listOf(
            moviesSearchFactory.create(MovieSearchFactory.Poster.CaptainAmerica),
            moviesSearchFactory.create(MovieSearchFactory.Poster.Avengers),
        )

        //Then
        Truth.assertThat(
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
        whenever(remoteDataSource.getSearchMovie(any(), any()))
            .thenThrow(exception)

        //When
        val result = movieSearchPagingSource.load(
            //É chamado sempre que é feito uma requisição da primeira página
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = moviesSearchPagingFactory.totalResults,
                placeholdersEnabled = false
            )
        )

        //Then
        Truth.assertThat(PagingSource.LoadResult.Error<Int, Movie>(exception)).isEqualTo(result)
    }
}
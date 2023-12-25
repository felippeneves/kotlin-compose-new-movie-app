package com.felippeneves.newmovieapp.search_movie_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.felippeneves.newmovieapp.R
import com.felippeneves.newmovieapp.core.presentation.components.common.MovieAppBar
import com.felippeneves.newmovieapp.search_movie_feature.presentation.components.SearchContent
import com.felippeneves.newmovieapp.search_movie_feature.presentation.state.MovieSearchState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearchScreen(
    uiState: MovieSearchState,
    onEvent: (MovieSearchEvent) -> Unit,
    onFetch: (String) -> Unit,
    navigateToMovieDetails: (Int) -> Unit
) {
    val pagingMovies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(title = R.string.search_movies)
        },
        content = { paddingValues ->
            SearchContent(
                paddingValues = paddingValues,
                pagingMovies = pagingMovies,
                query = uiState.query,
                onSearch = {
                    onFetch(it)
                },
                onEvent = {
                    onEvent(it)
                },
                onDetail = { movieId ->
                    navigateToMovieDetails(movieId)
                }
            )
        }
    )
}
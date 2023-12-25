package com.felippeneves.newmovieapp.movie_details_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.felippeneves.newmovieapp.R
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.presentation.components.common.MovieAppBar
import com.felippeneves.newmovieapp.movie_details_feature.presentation.components.MovieDetailsContent
import com.felippeneves.newmovieapp.movie_details_feature.presentation.state.MovieDetailsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    uiState: MovieDetailsState,
    onHandleFavorite: (Movie) -> Unit
) {
    val pagingMoviesSimilar = uiState.results.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(title = R.string.movie_details)
        },
        content = { paddingValues ->
            MovieDetailsContent(
                movieDetails = uiState.movieDetails,
                pagingMoviesSimilar = pagingMoviesSimilar,
                isLoading = uiState.isLoading,
                isError = uiState.error,
                iconColor = uiState.iconColor,
                paddingValues = paddingValues,
                onHandleFavorite = { movie ->
                    onHandleFavorite(movie)
                }
            )
        }
    )
}
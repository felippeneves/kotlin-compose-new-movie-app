package com.felippeneves.newmovieapp.movie_popular_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.felippeneves.newmovieapp.R
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.presentation.components.common.MovieAppBar
import com.felippeneves.newmovieapp.core.util.UtilFunctions
import com.felippeneves.newmovieapp.movie_popular_feature.presentation.components.MovieContent
import com.felippeneves.newmovieapp.movie_popular_feature.presentation.state.MoviePopularState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviePopularScreen(
    uiState: MoviePopularState,
    navigateToMovieDetails: (Int) -> Unit
) {
    val movies: LazyPagingItems<Movie> = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(title = R.string.popular_movies)
        },
        content = { paddingValues ->
            MovieContent(
                pagingMovies = movies,
                paddingValues = paddingValues,
                onClick = { movieId ->
                    UtilFunctions.logInfo("MOVIE_ID", movieId.toString())
                    navigateToMovieDetails(movieId)
                }
            )
        }
    )
}
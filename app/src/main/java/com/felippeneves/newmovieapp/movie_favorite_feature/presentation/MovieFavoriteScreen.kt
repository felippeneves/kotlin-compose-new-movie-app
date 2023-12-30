package com.felippeneves.newmovieapp.movie_favorite_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.felippeneves.newmovieapp.R
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.presentation.components.common.MovieAppBar
import com.felippeneves.newmovieapp.movie_favorite_feature.presentation.components.MovieFavoriteContent
import com.felippeneves.newmovieapp.movie_favorite_feature.presentation.state.MovieFavoriteState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieFavoriteScreen(
    movies: List<Movie>,
    navigateToDetailsMovie: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            MovieAppBar(title = R.string.favorite_movies)
        },
        content = { paddingValues ->
            MovieFavoriteContent(
                paddingValues = paddingValues,
                movies = movies,
                onClick = { movieId ->
                    navigateToDetailsMovie(movieId)
                }
            )
        }
    )
}

@Preview
@Composable
private fun MovieFavoriteScreenPreview() {
    MovieFavoriteScreen(
        movies = listOf(
            Movie(
                id = 1,
                title = "Spider-Man: Far From Home",
                imageUrl = ""
            ),
            Movie(
                id = 2,
                title = "Captain America: The Winter Soldier",
                imageUrl = ""
            ),
            Movie(
                id = 3,
                title = "Avengers: Infinity War",
                imageUrl = ""
            )
        ),
        navigateToDetailsMovie = { }
    )
}
package com.felippeneves.newmovieapp.movie_favorite_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.felippeneves.newmovieapp.R
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.movie_favorite_feature.presentation.components.MovieFavoriteContent
import com.felippeneves.newmovieapp.movie_favorite_feature.presentation.state.MovieFavoriteState
import com.felippeneves.newmovieapp.ui.theme.black
import com.felippeneves.newmovieapp.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieFavoriteScreen(
    uiState: MovieFavoriteState,
    navigateToDetailsMovie: (Int) -> Unit
) {
    val movies = uiState.movies

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.favorite_movies),
                        color = white
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = black
                )
            )
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
fun MovieFavoriteScreenPreview() {
    MovieFavoriteScreen(
        uiState = MovieFavoriteState(
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
            )
        ),
        navigateToDetailsMovie = { }
    )
}
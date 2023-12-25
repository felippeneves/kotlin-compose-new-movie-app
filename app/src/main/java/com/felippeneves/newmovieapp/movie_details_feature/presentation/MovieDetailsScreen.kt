package com.felippeneves.newmovieapp.movie_details_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.felippeneves.newmovieapp.R
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.movie_details_feature.presentation.components.MovieDetailsContent
import com.felippeneves.newmovieapp.movie_details_feature.presentation.state.MovieDetailsState
import com.felippeneves.newmovieapp.ui.theme.black
import com.felippeneves.newmovieapp.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    id: Int?,
    uiState: MovieDetailsState,
    getMovieDetails: (MovieDetailsEvent.GetMovieDetails) -> Unit,
    onHandleFavorite: (Movie) -> Unit,
    checkedFavorite: (MovieDetailsEvent.CheckedFavorite) -> Unit
) {
    val pagingMoviesSimilar = uiState.results.collectAsLazyPagingItems()

    //Cria um fluxo assíncrono separado para poder buscar os detalhes do filme
    //Com o parâmetro key1 = true, é garantido que o bloco de código abaixo seja executado sempre quando a função MovieDetailsScreen()
    //for chamada
    LaunchedEffect(key1 = true) {
        if (id != null) {
            getMovieDetails(MovieDetailsEvent.GetMovieDetails(movieId = id))
            checkedFavorite(MovieDetailsEvent.CheckedFavorite(movieId = id))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.movie_details),
                        color = white
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = black
                )
            )
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
package com.felippeneves.newmovieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.felippeneves.newmovieapp.core.util.Constants
import com.felippeneves.newmovieapp.movie_details_feature.presentation.MovieDetailsScreen
import com.felippeneves.newmovieapp.movie_details_feature.presentation.MovieDetailsViewModel
import com.felippeneves.newmovieapp.movie_favorite_feature.presentation.MovieFavoriteScreen
import com.felippeneves.newmovieapp.movie_favorite_feature.presentation.MovieFavoriteViewModel
import com.felippeneves.newmovieapp.movie_popular_feature.presentation.MoviePopularScreen
import com.felippeneves.newmovieapp.movie_popular_feature.presentation.MoviePopularViewModel
import com.felippeneves.newmovieapp.movie_popular_feature.presentation.state.MoviePopularState
import com.felippeneves.newmovieapp.movie_search_feature.presentation.MovieSearchEvent
import com.felippeneves.newmovieapp.movie_search_feature.presentation.MovieSearchScreen
import com.felippeneves.newmovieapp.movie_search_feature.presentation.MovieSearchViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.PopularMovie.route
    ) {
        composable(route = BottomNavItem.PopularMovie.route) {

            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState: MoviePopularState = viewModel.uiState

            MoviePopularScreen(
                uiState = uiState,
                navigateToMovieDetails = { movieId ->
                    navController.navigate(
                        route = DetailsScreenNav.DetailsScreen.passMovieId(
                            movieId = movieId
                        )
                    )
                }
            )
        }

        composable(route = BottomNavItem.SearchMovie.route) {

            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onEvent: (MovieSearchEvent) -> Unit = viewModel::event
            val onFetch: (String) -> Unit = viewModel::fetch

            MovieSearchScreen(
                uiState = uiState,
                onEvent = onEvent,
                onFetch = onFetch,
                navigateToMovieDetails = { movieId ->
                    navController.navigate(
                        route = DetailsScreenNav.DetailsScreen.passMovieId(
                            movieId = movieId
                        )
                    )
                }
            )
        }

        composable(route = BottomNavItem.FavoriteMovie.route) {
            val viewModel: MovieFavoriteViewModel = hiltViewModel()
            //Diferente do collectAsState, esse collect conhece o ciclo de vida da UI, portanto,
            //quando o aplicativo estiver em segundo plano ele não manterá a coleta de fluxo ativo,
            //dessa maneira não desperdiça recurso
            val uiState =
                viewModel.uiState.movies.collectAsStateWithLifecycle(initialValue = emptyList())

            MovieFavoriteScreen(
                movies = uiState.value,
                navigateToDetailsMovie = { movieId ->
                    navController.navigate(
                        route = DetailsScreenNav.DetailsScreen.passMovieId(
                            movieId = movieId
                        )
                    )
                }
            )
        }

        composable(
            route = DetailsScreenNav.DetailsScreen.route,
            arguments = listOf(
                navArgument(Constants.MOVIE_DETAIL_ARGUMENT_KEY) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            val viewModel: MovieDetailsViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onHandleFavorite = viewModel::onHandleFavorite

            MovieDetailsScreen(
                uiState = uiState,
                onHandleFavorite = onHandleFavorite
            )
        }
    }
}
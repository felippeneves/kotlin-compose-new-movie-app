package com.felippeneves.newmovieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.felippeneves.newmovieapp.search_movie_feature.presentation.MovieSearchEvent
import com.felippeneves.newmovieapp.search_movie_feature.presentation.MovieSearchScreen
import com.felippeneves.newmovieapp.search_movie_feature.presentation.MovieSearchViewModel

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
                    navController.navigate(route = BottomNavItem.MovieDetails.passMovieId(movieId = movieId))
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
                    navController.navigate(route = BottomNavItem.MovieDetails.passMovieId(movieId = movieId))
                }
            )
        }

        composable(route = BottomNavItem.FavoriteMovie.route) {
            val viewModel: MovieFavoriteViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MovieFavoriteScreen(
                uiState = uiState,
                navigateToDetailsMovie = { movieId ->
                    navController.navigate(route = BottomNavItem.MovieDetails.passMovieId(movieId = movieId))
                }
            )
        }

        composable(
            route = BottomNavItem.MovieDetails.route,
            arguments = listOf(
                navArgument(Constants.MOVIE_DETAIL_ARGUMENT_KEY) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { navBackStackEntry ->
            val viewModel: MovieDetailsViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val getMovieDetails = viewModel::getMovieDetails
            val onHandleFavorite = viewModel::onHandleFavorite
            val checkedFavorite = viewModel::checkedFavorite
            val id = navBackStackEntry.arguments?.getInt(Constants.MOVIE_DETAIL_ARGUMENT_KEY)

            MovieDetailsScreen(
                id = id,
                uiState = uiState,
                getMovieDetails = getMovieDetails,
                onHandleFavorite = onHandleFavorite,
                checkedFavorite = checkedFavorite
            )
        }
    }
}
package com.felippeneves.newmovieapp.core.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.felippeneves.newmovieapp.core.util.Constants.MOVIE_DETAIL_ARGUMENT_KEY

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    object PopularMovie : BottomNavItem(
        title = "Popular Movies",
        icon = Icons.Default.Movie,
        route = "popular_movie_screen"
    )

    object SearchMovie : BottomNavItem(
        title = "Search",
        icon = Icons.Default.Search,
        route = "search_movie_screen"
    )

    object FavoriteMovie : BottomNavItem(
        title = "Favorites",
        icon = Icons.Default.Favorite,
        route = "favorite_movie_screen"
    )
}

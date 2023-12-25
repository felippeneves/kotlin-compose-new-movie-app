package com.felippeneves.newmovieapp.core.presentation.navigation

import com.felippeneves.newmovieapp.core.util.Constants

sealed class DetailsScreenNav(val route: String) {
    object DetailsScreen : DetailsScreenNav(
        route = "details_movie_destination?${Constants.MOVIE_DETAIL_ARGUMENT_KEY}={${Constants.MOVIE_DETAIL_ARGUMENT_KEY}}"
    ) {
        fun passMovieId(movieId: Int) =
            "details_movie_destination?${Constants.MOVIE_DETAIL_ARGUMENT_KEY}=$movieId"
    }
}
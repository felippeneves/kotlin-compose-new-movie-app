package com.felippeneves.newmovieapp.movie_details_feature.presentation

import com.felippeneves.newmovieapp.core.domain.model.Movie

sealed class MovieDetailsEvent {
    data class GetMovieDetails(val movieId: Int) : MovieDetailsEvent()
    data class AddFavorite(val movie: Movie) : MovieDetailsEvent()
    data class CheckedFavorite(val movieId: Int) : MovieDetailsEvent()
    data class RemoveFavorite(val movie: Movie) : MovieDetailsEvent()
}
package com.felippeneves.newmovieapp.movie_favorite_feature.presentation.state

import com.felippeneves.newmovieapp.core.domain.model.Movie

data class MovieFavoriteState(
    val movies: List<Movie> = emptyList()
)

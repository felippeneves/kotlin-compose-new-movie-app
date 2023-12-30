package com.felippeneves.newmovieapp.movie_favorite_feature.presentation.state

import com.felippeneves.newmovieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieFavoriteState(
    val movies: Flow<List<Movie>> = emptyFlow()
)

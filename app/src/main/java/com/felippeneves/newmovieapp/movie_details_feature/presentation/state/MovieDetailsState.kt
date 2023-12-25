package com.felippeneves.newmovieapp.movie_details_feature.presentation.state

import androidx.compose.ui.graphics.Color
import androidx.paging.PagingData
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.domain.model.MovieDetails
import com.felippeneves.newmovieapp.ui.theme.white
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieDetailsState(
    val movieDetails: MovieDetails? = null,
    val error: String = "",
    val isLoading: Boolean = false,
    val iconColor: Color = white,
    val results: Flow<PagingData<Movie>> = emptyFlow()
)

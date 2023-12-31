package com.felippeneves.newmovieapp.movie_search_feature.presentation

sealed class MovieSearchEvent {
    data class EnteredQuery(val value: String) : MovieSearchEvent()
}
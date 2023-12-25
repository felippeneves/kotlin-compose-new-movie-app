package com.felippeneves.newmovieapp.search_movie_feature.data.mapper

import com.felippeneves.newmovieapp.core.data.remote.model.MovieSearchResult
import com.felippeneves.newmovieapp.core.domain.model.MovieSearch
import com.felippeneves.newmovieapp.core.util.toPostUrl

fun List<MovieSearchResult>.toMovieSearch() = map { movieSearchResult ->
    MovieSearch(
        id = movieSearchResult.id,
        imageUrl = movieSearchResult.posterPath.toPostUrl(),
        voteAverage = movieSearchResult.voteAverage
    )
}
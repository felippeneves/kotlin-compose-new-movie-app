package com.felippeneves.newmovieapp.movie_popular_feature.data.mapper

import com.felippeneves.newmovieapp.core.data.remote.model.MovieResult
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.util.toPostUrl

fun List<MovieResult>.toMovie() = map { movieResult ->
    Movie(
        id = movieResult.id,
        title = movieResult.title,
        voteAverage = movieResult.voteAverage,
        imageUrl = movieResult?.posterPath?.toPostUrl() ?: ""
    )
}
package com.felippeneves.newmovieapp.movie_popular_feature.data.mapper

import com.felippeneves.newmovieapp.core.data.remote.model.MovieResult
import com.felippeneves.newmovieapp.core.data.remote.response.MovieResponse
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.domain.model.MoviePaging
import com.felippeneves.newmovieapp.core.util.toPostUrl

fun MovieResult.toMovie(): Movie =
    Movie(
        id = id,
        title = title,
        voteAverage = voteAverage,
        imageUrl = posterPath?.toPostUrl() ?: ""
    )

fun List<MovieResult>.toMovie() = map { it.toMovie() }

fun MovieResponse.toMoviePaging() =
    MoviePaging(
        page = page,
        totalPages = totalPages,
        totalResults = totalResults,
        movies = results.map { it.toMovie() }
    )
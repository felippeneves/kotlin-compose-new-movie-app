package com.felippeneves.newmovieapp.movie_search_feature.data.mapper

import com.felippeneves.newmovieapp.core.data.remote.model.MovieSearchResult
import com.felippeneves.newmovieapp.core.data.remote.response.MovieSearchResponse
import com.felippeneves.newmovieapp.core.domain.model.MovieSearch
import com.felippeneves.newmovieapp.core.domain.model.MovieSearchPaging
import com.felippeneves.newmovieapp.core.util.toPostUrl

fun MovieSearchResult.toMovieSearch() =
    MovieSearch(
        id = id,
        imageUrl = posterPath.toPostUrl(),
        voteAverage = voteAverage
    )

fun List<MovieSearchResult>.toMovieSearch() = map { it.toMovieSearch() }

fun MovieSearchResponse.toMovieSearchPaging() =
    MovieSearchPaging(
        page = page,
        totalPages = totalPages,
        totalResults = totalResults,
        movies = results.toMovieSearch()
    )
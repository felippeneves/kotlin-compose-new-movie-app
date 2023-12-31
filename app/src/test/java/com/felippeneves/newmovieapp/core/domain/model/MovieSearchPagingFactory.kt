package com.felippeneves.newmovieapp.core.domain.model

class MovieSearchPagingFactory {

    fun create() = MovieSearchPaging(
        page = 1,
        totalPages = 1,
        totalResults = 2,
        movies = listOf(
            MovieSearch(
                id = 1,
                voteAverage = 9.9,
                imageUrl = "url"
            ),
            MovieSearch(
                id = 2,
                voteAverage = 9.8,
                imageUrl = "url"
            )
        )
    )
}
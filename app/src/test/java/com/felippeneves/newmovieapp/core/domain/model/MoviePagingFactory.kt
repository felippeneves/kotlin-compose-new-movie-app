package com.felippeneves.newmovieapp.core.domain.model

class MoviePagingFactory {

    fun create() = MoviePaging(
        page = 1,
        totalPages = 1,
        totalResults = 2,
        movies = listOf(
            Movie(
                id = 1,
                title = "Captain America",
                voteAverage = 9.9,
                imageUrl = "url"
            ),
            Movie(
                id = 2,
                title = "Avengers",
                voteAverage = 9.8,
                imageUrl = "url"
            )
        )
    )
}
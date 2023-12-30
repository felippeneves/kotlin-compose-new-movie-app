package com.felippeneves.newmovieapp.core.domain.model

class MovieSearchFactory {

    fun create(poster: Poster) = when (poster) {
        Poster.CaptainAmerica -> {
            MovieSearch(
                id = 1,
                voteAverage = 9.9,
                imageUrl = "url"
            )
        }

        Poster.Avengers -> {
            MovieSearch(
                id = 1,
                voteAverage = 9.8,
                imageUrl = "url"
            )
        }

        Poster.SpiderMan -> {
            MovieSearch(
                id = 1,
                voteAverage = 9.7,
                imageUrl = "url"
            )
        }
    }

    sealed class Poster {
        object CaptainAmerica : Poster()
        object Avengers : Poster()
        object SpiderMan : Poster()
    }
}
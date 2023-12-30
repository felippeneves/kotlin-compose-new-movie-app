package com.felippeneves.newmovieapp.core.domain.model

class MovieFactory {

    fun create(poster: Poster) = when (poster) {
        Poster.CaptainAmerica -> {
            Movie(
                id = 1,
                title = "Captain America",
                voteAverage = 9.9,
                imageUrl = "url"
            )
        }

        Poster.Avengers -> {
            Movie(
                id = 1,
                title = "Avengers",
                voteAverage = 9.8,
                imageUrl = "url"
            )
        }

        Poster.SpiderMan -> {
            Movie(
                id = 1,
                title = "Spider Man",
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
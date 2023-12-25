package com.felippeneves.newmovieapp.movie_details_feature.data.mapper

import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.domain.model.MovieDetails

fun MovieDetails.toMovie(): Movie =
    Movie(
        id = id,
        title = title,
        imageUrl = backdropPathUrl.toString(),
        voteAverage = voteAverage
    )
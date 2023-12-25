package com.felippeneves.newmovieapp.movie_favorite_feature.data.mapper

import com.felippeneves.newmovieapp.core.data.local.entity.MovieEntity
import com.felippeneves.newmovieapp.core.domain.model.Movie

fun List<MovieEntity>.toMovies() = map { movieEntity ->
    Movie(
        id = movieEntity.movieId,
        title = movieEntity.title,
        imageUrl = movieEntity.imageUrl
    )
}

fun Movie.toMovieEntity() =
    MovieEntity(
        movieId = id,
        title = title,
        imageUrl = imageUrl
    )
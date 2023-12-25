package com.felippeneves.newmovieapp.movie_favorite_feature.data.source

import com.felippeneves.newmovieapp.core.data.local.dao.MovieDao
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.movie_favorite_feature.data.mapper.toMovieEntity
import com.felippeneves.newmovieapp.movie_favorite_feature.data.mapper.toMovies
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.source.MovieFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieFavoriteLocalDataSourceImpl @Inject constructor(
    private val dao: MovieDao
) : MovieFavoriteLocalDataSource {
    override fun getMovies(): Flow<List<Movie>> =
        dao.getMovies().map { list ->
            list.toMovies()
        }

    override suspend fun insert(movie: Movie) {
        dao.insert(entity = movie.toMovieEntity())
    }

    override suspend fun delete(movie: Movie) {
        dao.delete(entity = movie.toMovieEntity())
    }

    override suspend fun isFavorite(movieId: Int): Boolean =
        dao.countMovie(movieId = movieId) > 0
}
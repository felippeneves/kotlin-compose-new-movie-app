package com.felippeneves.newmovieapp.movie_details_feature.domain.repository

import androidx.paging.PagingSource
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.domain.model.MovieDetails

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): MovieDetails
    fun getMoviesSimilar(movieId: Int): PagingSource<Int, Movie>
}
package com.felippeneves.newmovieapp.movie_popular_feature.domain.repository

import androidx.paging.PagingSource
import com.felippeneves.newmovieapp.core.domain.model.Movie

interface MoviePopularRepository {
    fun getPopularMovies(): PagingSource<Int, Movie>
}
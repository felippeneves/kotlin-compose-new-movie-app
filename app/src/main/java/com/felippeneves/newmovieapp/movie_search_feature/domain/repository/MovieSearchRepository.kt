package com.felippeneves.newmovieapp.movie_search_feature.domain.repository

import androidx.paging.PagingSource
import com.felippeneves.newmovieapp.core.domain.model.MovieSearch

interface MovieSearchRepository {
    fun getSearchMovies(query: String): PagingSource<Int, MovieSearch>
}
package com.felippeneves.newmovieapp.core.data.remote.api

import com.felippeneves.newmovieapp.core.data.remote.response.MovieDetailResponse
import com.felippeneves.newmovieapp.core.data.remote.response.MovieResponse
import com.felippeneves.newmovieapp.core.data.remote.response.MovieSearchResponse
import com.felippeneves.newmovieapp.core.util.ApiInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("${ApiInfo.MOVIE_ENDPOINT}/${ApiInfo.POPULAR_ENDPOINT}")
    suspend fun getPopularMovies(
        @Query(ApiInfo.PAGE_PARAM) page: Int
    ): MovieResponse

    @GET("${ApiInfo.SEARCH_ENDPOINT}/${ApiInfo.MULTI_ENDPOINT}")
    suspend fun searchMovie(
        @Query(ApiInfo.PAGE_PARAM) page: Int,
        @Query(ApiInfo.QUERY_PARAM) query: String
    ): MovieSearchResponse

    @GET("${ApiInfo.MOVIE_ENDPOINT}/${ApiInfo.MOVIE_ID_PARAM_ENDPOINT}")
    suspend fun getMovie(
        @Path(ApiInfo.MOVIE_ID_PARAM) movieId: Int
    ): MovieDetailResponse

    @GET("${ApiInfo.MOVIE_ENDPOINT}/${ApiInfo.MOVIE_ID_PARAM_ENDPOINT}/${ApiInfo.SIMILAR_ENDPOINT}")
    suspend fun getMoviesSimilar(
        @Path(ApiInfo.MOVIE_ID_PARAM) movieId: Int,
        @Query(ApiInfo.PAGE_PARAM) page: Int
    ): MovieResponse
}
package com.felippeneves.newmovieapp.core.data.remote.response

import com.felippeneves.newmovieapp.core.data.remote.model.MovieSearchResult
import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieSearchResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
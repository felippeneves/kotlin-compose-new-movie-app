package com.felippeneves.newmovieapp.core.util

object ApiInfo {

    //region endpoint

    const val MOVIE_ENDPOINT = "movie"
    const val POPULAR_ENDPOINT = "popular"
    const val MULTI_ENDPOINT = "multi"
    const val SIMILAR_ENDPOINT = "similar"
    const val SEARCH_ENDPOINT = "search"

    //endregion endpoint

    //region Parameter

    const val PAGE_PARAM = "page"
    const val QUERY_PARAM = "query"
    const val MOVIE_ID_PARAM = "movie_id"
    const val LANGUAGE_PARAM = "language"
    const val API_KEY_PARAM = "api_key"

    //endregion Parameter

    //region Parameter endpoint

    const val MOVIE_ID_PARAM_ENDPOINT = "{movie_id}"

    //endregion  Parameter endpoint

    //region Parameter Value

    const val LANGUAGE_PARAM_VALUE = "pt-BR"

    //endregion Parameter Value
}
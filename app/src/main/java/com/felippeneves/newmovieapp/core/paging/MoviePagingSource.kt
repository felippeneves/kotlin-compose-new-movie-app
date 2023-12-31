package com.felippeneves.newmovieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource

class MoviePagingSource(
    private val remoteDataSource: MoviePopularRemoteDataSource
) : PagingSource<Int, Movie>() {
    //Método utilizado para atualizar os dados da fonte de dados, quando o usuário solicitar uma atualização, o paging 3
    //utiliza a chave retornada para verificar se há mais dados disponíveis
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: 1
            val moviePaging = remoteDataSource.getPopularMovies(page = pageNumber)

            val movies = moviePaging.movies
            val totalPages = moviePaging.totalPages

            LoadResult.Page(
                data = movies,
                //Chave de paginação anterior é nula quando é a primeira página
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                //Verifica se o número da página atual é igual ao número do total de páginas recebido pela API
                nextKey = if (pageNumber == totalPages) null else pageNumber + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}
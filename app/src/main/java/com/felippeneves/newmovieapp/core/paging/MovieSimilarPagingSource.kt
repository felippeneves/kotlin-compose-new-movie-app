package com.felippeneves.newmovieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.movie_details_feature.domain.source.MovieDetailsRemoteDataSource
import com.felippeneves.newmovieapp.movie_popular_feature.data.mapper.toMovie
import retrofit2.HttpException
import java.io.IOException

class MovieSimilarPagingSource(
    private val remoteDataSource: MovieDetailsRemoteDataSource,
    private val movieId: Int
) : PagingSource<Int, Movie>() {
    //Método utilizado para atualizar os dados da fonte de dados, quando o usuário solicitar uma atualização, o paging 3
    //utiliza a chave retornada para verificar se há mais dados disponíveis
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(
                LIMIT
            )
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getMoviesSimilar(page = pageNumber, movieId = movieId)
            val movies = response.results

            LoadResult.Page(
                data = movies.toMovie(),
                //Chave de paginação anterior é nula quando é a primeira página
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                //Chave de paginação seguinte é nula quando a lista de filmes for vazia, pois não
                //há mais páginas para carregar
                nextKey = if (movies.isEmpty()) null else pageNumber + 1
            )
        } catch (exception: IOException) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}
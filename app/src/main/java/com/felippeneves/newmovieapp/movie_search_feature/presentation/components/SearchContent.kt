package com.felippeneves.newmovieapp.movie_search_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.felippeneves.newmovieapp.R
import com.felippeneves.newmovieapp.core.domain.model.MovieSearch
import com.felippeneves.newmovieapp.core.presentation.components.common.ErrorScreen
import com.felippeneves.newmovieapp.core.presentation.components.common.LoadingView
import com.felippeneves.newmovieapp.movie_popular_feature.presentation.components.MovieItem
import com.felippeneves.newmovieapp.movie_search_feature.presentation.MovieSearchEvent
import com.felippeneves.newmovieapp.ui.theme.black

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    pagingMovies: LazyPagingItems<MovieSearch>,
    query: String,
    onSearch: (String) -> Unit,
    onEvent: (MovieSearchEvent) -> Unit,
    onDetail: (movieId: Int) -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(black)
            .padding(paddingValues),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        SearchComponent(
            query = query,
            onSearch = {
                isLoading = true
                onSearch(it)
            },
            onQueryChangeEvent = {
                onEvent(it)
            },
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 8.dp
            )
        )
        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center
        ) {
            items(pagingMovies.itemCount) { index ->
                val movie = pagingMovies[index]
                movie?.let {
                    MovieItem(
                        voteAverage = it.voteAverage,
                        imageUrl = it.imageUrl,
                        id = it.id,
                        onClick = { movieId ->
                            onDetail(movieId)
                        }
                    )
                }
                isLoading = false
            }

            pagingMovies.apply {
                when {
                    isLoading -> {
                        item(
                            span = {
                                //Define que o item ocupe o máximo do tamanho da linha e com isso fique centralizado na tela
                                GridItemSpan(maxLineSpan)
                            }
                        ) {
                            LoadingView()
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        isLoading = false
                        item(
                            span = {
                                //Define que o item ocupe o máximo do tamanho da linha e com isso fique centralizado na tela
                                GridItemSpan(maxLineSpan)
                            },
                        ) {
                            ErrorScreen(
                                message = stringResource(id = R.string.check_your_internet_connection),
                                retry = {
                                    //Faz a requisição novamente
                                    retry()
                                }
                            )
                        }
                    }

                    //Se ocorrer um erro quando está ocorrendo o scroll e buscando mais informações na API
                    loadState.append is LoadState.Error -> {
                        isLoading = false
                        //TODO: Verificar se o jeito que foi definido está bom
                        item(
                            span = {
                                //Define que o item ocupe o máximo do tamanho da linha e com isso fique centralizado na tela
                                GridItemSpan(maxLineSpan)
                            }
                        ) {
                            ErrorScreen(
                                message = stringResource(id = R.string.check_your_internet_connection),
                                retry = {
                                    //Faz a requisição novamente
                                    retry()
                                },
//                                modifier = Modifier
//                                    .zIndex(2f)
//                                    .fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}
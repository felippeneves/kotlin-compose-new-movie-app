package com.felippeneves.newmovieapp.movie_popular_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.felippeneves.newmovieapp.R
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.presentation.components.common.ErrorScreen
import com.felippeneves.newmovieapp.core.presentation.components.common.LoadingView

@Composable
fun MovieContent(
    modifier: Modifier = Modifier,
    pagingMovies: LazyPagingItems<Movie>,
    paddingValues: PaddingValues,
    onClick: (movieId: Int) -> Unit
) {
    Box(modifier = Modifier.background(Color.Black)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            items(pagingMovies.itemCount) { index ->
                val movie = pagingMovies[index]
                movie?.let {
                    MovieItem(
                        voteAverage = it.voteAverage,
                        imageUrl = it.imageUrl,
                        id = it.id,
                        onClick = { movieId ->
                            onClick(movieId)
                        }
                    )
                }
            }
            pagingMovies.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item(
                            span = {
                                //Define que o item ocupe o máximo do tamanho da linha e com isso fique centralizado na tela
                                GridItemSpan(maxLineSpan)
                            }
                        ) {
                            LoadingView()
                        }
                    }

                    //Quando está ocorrendo o scroll e buscando mais informações na API
                    loadState.append is LoadState.Loading -> {
                        //TODO: Verificar se o jeito que foi definido está bom
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
                                modifier = Modifier.zIndex(2f).fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}
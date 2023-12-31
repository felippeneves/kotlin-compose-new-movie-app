package com.felippeneves.newmovieapp.movie_popular_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.felippeneves.newmovieapp.core.util.getPagingConfig
import com.felippeneves.newmovieapp.movie_popular_feature.domain.usecase.GetPopularMoviesUseCase
import com.felippeneves.newmovieapp.movie_popular_feature.presentation.state.MoviePopularState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviePopularViewModel @Inject constructor(
    getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    var uiState by mutableStateOf(MoviePopularState())
        private set

    init {
        val movies = getPopularMoviesUseCase.invoke(
            params = GetPopularMoviesUseCase.Params(
                pagingConfig = getPagingConfig()
            )
        )
            //Armazena os dados em cache os resultados do Flow para reutilização em outro
            //lugar do projeto. Quando o Flow for recuperar os dados em outra parte do código
            //os dados serão recuperados  do cache, ao invés de serem recuperados da API
            .cachedIn(viewModelScope)
        //Cria uma cópia imutável do objeto movies. Todas as demais propriedades são
        //mantidas inalteradas
        uiState = uiState.copy(movies = movies)
    }
}
package com.felippeneves.newmovieapp.movie_details_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.util.DataResult
import com.felippeneves.newmovieapp.core.util.UtilFunctions
import com.felippeneves.newmovieapp.movie_details_feature.domain.usecase.GetMovieDetailsUseCase
import com.felippeneves.newmovieapp.movie_details_feature.presentation.state.MovieDetailsState
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import com.felippeneves.newmovieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addMovieFavoriteUseCase: AddMovieFavoriteUseCase,
    private val deleteMovieFavoriteUseCase: DeleteMovieFavoriteUseCase,
    private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MovieDetailsState())
        private set

    fun getMovieDetails(getMovieDetails: MovieDetailsEvent.GetMovieDetails) =
        event(getMovieDetails)

    fun checkedFavorite(checkedFavorite: MovieDetailsEvent.CheckedFavorite) =
        event(checkedFavorite)

    fun onHandleFavorite(movie: Movie) {
        if (uiState.iconColor == Color.White) {
            event(MovieDetailsEvent.AddFavorite(movie = movie))
        } else {
            event(MovieDetailsEvent.RemoveFavorite(movie = movie))
        }
    }

    private fun event(event: MovieDetailsEvent) {
        when (event) {
            is MovieDetailsEvent.GetMovieDetails -> {
                viewModelScope.launch {
                    getMovieDetailsUseCase.invoke(
                        params = GetMovieDetailsUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collect { dataResult ->
                        when (dataResult) {
                            is DataResult.Success -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    movieDetails = dataResult.data?.second,
                                    results = dataResult.data?.first ?: emptyFlow()
                                )
                            }

                            is DataResult.Failure -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    error = dataResult.e?.message.toString()
                                )
                                UtilFunctions.logError(
                                    tag = "DETAIL ERROR",
                                    message = dataResult.e?.message.toString()
                                )
                            }

                            is DataResult.Loading -> {
                                uiState = uiState.copy(
                                    isLoading = true
                                )
                            }
                        }
                    }
                }
            }

            is MovieDetailsEvent.AddFavorite -> {
                viewModelScope.launch {
                    addMovieFavoriteUseCase.invoke(
                        params = AddMovieFavoriteUseCase.Params(
                            movie = event.movie
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is DataResult.Success -> {
                                uiState = uiState.copy(iconColor = Color.Red)
                            }

                            is DataResult.Failure -> {
                                UtilFunctions.logError(
                                    tag = "DETAIL ERROR",
                                    message = "Error when registering the film"
                                )
                            }

                            is DataResult.Loading -> {}
                        }
                    }
                }
            }

            is MovieDetailsEvent.CheckedFavorite -> {
                viewModelScope.launch {
                    isMovieFavoriteUseCase.invoke(
                        params = IsMovieFavoriteUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is DataResult.Success -> {
                                uiState = if (result.data == true) {
                                    uiState.copy(iconColor = Color.Red)
                                } else {
                                    uiState.copy(iconColor = Color.White)
                                }
                            }

                            is DataResult.Failure -> {
                                UtilFunctions.logError(
                                    tag = "DETAIL ERROR",
                                    message = "Error when checking favorite movie"
                                )
                            }

                            is DataResult.Loading -> {}
                        }
                    }
                }
            }

            is MovieDetailsEvent.RemoveFavorite -> {
                viewModelScope.launch {
                    deleteMovieFavoriteUseCase.invoke(
                        params = DeleteMovieFavoriteUseCase.Params(
                            movie = event.movie
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is DataResult.Success -> {
                                uiState = uiState.copy(iconColor = Color.White)
                            }

                            is DataResult.Failure -> {
                                UtilFunctions.logError(
                                    tag = "DETAIL ERROR",
                                    message = "Error when deleting favorite movie"
                                )
                            }

                            is DataResult.Loading -> {}
                        }
                    }
                }
            }
        }
    }
}
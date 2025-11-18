package com.example.mvvm_compose_di.ui.screens.movie_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mvvm_compose_di.data.model.MovieDetail
import com.example.mvvm_compose_di.data.model.MovieItem
import com.example.mvvm_compose_di.data.repository.movie.MoviesRepository
import com.example.mvvm_compose_di.utils.networkutils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    val repo: MoviesRepository
) : ViewModel() {

    private val _movieDetail = MutableStateFlow<DataState<MovieDetail>>(DataState.Loading)
    val movieDetail : StateFlow<DataState<MovieDetail>> = _movieDetail.asStateFlow()

    private val _recommendedMovies = MutableStateFlow<DataState<List<MovieItem>>>(DataState.Loading)
    val recommendedMovies: StateFlow<DataState<List<MovieItem>>> = _recommendedMovies.asStateFlow()


    suspend fun fetchMovieDetails(movieId: Int){
        repo.fetchMovieDetails(movieId).collect {
            _movieDetail.value = it
        }
    }

    suspend fun fetchRecommendedMovies(movieId: Int){
        repo.fetchRecommendedMovies(movieId).collect {
            _recommendedMovies.value = it
        }
    }

}
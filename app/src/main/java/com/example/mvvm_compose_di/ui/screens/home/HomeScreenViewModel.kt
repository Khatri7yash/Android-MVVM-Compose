package com.example.mvvm_compose_di.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.example.mvvm_compose_di.data.repository.movie.MoviesRepository
import com.example.mvvm_compose_di.utils.networkutils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val repo: MoviesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DataState<Any>>(DataState.Loading)
    val uiState: StateFlow<DataState<Any>> = _uiState.asStateFlow()

    val movies = repo.getMovies().cachedIn(viewModelScope)

    suspend fun loadGenres() {
        viewModelScope.launch {
//            movieRepository.genreList()
        }
    }

    fun updateLoadState(loadState: CombinedLoadStates) {

        _uiState.value = when (loadState.refresh) {
            is LoadState.Error -> {
                DataState.Error((loadState.refresh as? LoadState.Error)?.error?.message ?: " ")
            }

            is LoadState.Loading -> {
                DataState.Loading
            }

            else -> DataState.Loading
        }
    }

}
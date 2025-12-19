package com.example.mvvm_compose_di.ui.screens.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mvvm_compose_di.data.model.MovieItem
import com.example.mvvm_compose_di.data.repository.movie.MoviesRepository
import com.example.mvvm_compose_di.utils.networkutils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(val repo: MoviesRepository) : ViewModel() {

    private val _loadingState = MutableStateFlow<DataState<Any>>(DataState.Loading)
    val loadingState = _loadingState.asStateFlow()

    private val _popularMoviesList = MutableStateFlow<PagingData<MovieItem>>(PagingData.empty())
    val popularMovies = _popularMoviesList.asStateFlow()


    fun getPopularMovies() {
        viewModelScope.launch {
            try {
                repo.getPopularMovies()
                    .distinctUntilChanged()
                    .cachedIn(viewModelScope)
                    .collect {
                        _popularMoviesList.value = it
                    }
            } catch (e: Exception) {
                _loadingState.value = DataState.Error(e.message ?: " ")
            }
        }
    }


    fun updateState(loadStates: LoadState) {
        _loadingState.value = when (loadStates) {
            is LoadState.Loading -> {
                DataState.Loading
            }

            is LoadState.NotLoading -> {
                DataState.Success(Any())
            }

            is LoadState.Error -> {
                DataState.Error((loadStates).error.message ?: " ")
            }
        }
    }
}
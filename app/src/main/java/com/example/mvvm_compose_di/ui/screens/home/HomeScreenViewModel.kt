package com.example.mvvm_compose_di.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.mvvm_compose_di.data.repository.movie.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val repo: MoviesRepository
) : ViewModel() {

    val movies = repo.getMovies().cachedIn(viewModelScope)

    suspend fun loadGenres() {
        viewModelScope.launch {
//            movieRepository.genreList()
        }
    }
}
package com.example.mvvm_compose_di.ui.screens.movie_detail

import androidx.lifecycle.ViewModel
import com.example.mvvm_compose_di.data.repository.movie.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    val repo: MoviesRepository
) : ViewModel() {
}
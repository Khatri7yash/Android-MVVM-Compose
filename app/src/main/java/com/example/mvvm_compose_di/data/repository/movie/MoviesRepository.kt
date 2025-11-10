package com.example.mvvm_compose_di.data.repository.movie

import androidx.paging.PagingData
import com.piashcse.hilt_mvvm_compose_movie.data.model.MovieItem
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMovies(): Flow<PagingData<MovieItem>>
}
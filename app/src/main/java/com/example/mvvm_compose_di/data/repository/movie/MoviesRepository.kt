package com.example.mvvm_compose_di.data.repository.movie

import androidx.paging.PagingData
import com.example.mvvm_compose_di.data.model.MovieItem
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMovies(): Flow<PagingData<MovieItem>>
}
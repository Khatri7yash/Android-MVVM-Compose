package com.example.mvvm_compose_di.data.repository.movie

import androidx.paging.PagingData
import com.example.mvvm_compose_di.data.model.MovieDetail
import com.example.mvvm_compose_di.data.model.MovieItem
import com.example.mvvm_compose_di.utils.networkutils.DataState
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMovies(): Flow<PagingData<MovieItem>>

    suspend fun fetchMovieDetails(movieId: Int): Flow<DataState<MovieDetail>>
    suspend fun fetchRecommendedMovies(movieId: Int): Flow<DataState<List<MovieItem>>>
}
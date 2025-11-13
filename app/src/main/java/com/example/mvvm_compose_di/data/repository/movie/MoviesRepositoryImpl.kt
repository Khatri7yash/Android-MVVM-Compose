package com.example.mvvm_compose_di.data.repository.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mvvm_compose_di.data.datasource.remote.APIServices
import com.example.mvvm_compose_di.data.datasource.remote.movies.NowPlayingMoviePagingDataSource
import com.example.mvvm_compose_di.data.model.MovieItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiService: APIServices
) : MoviesRepository {
    override fun getMovies(): Flow<PagingData<MovieItem>> =
        Pager(
            pagingSourceFactory = { NowPlayingMoviePagingDataSource(apiService) },
            config = PagingConfig(pageSize = 20)
        ).flow
}
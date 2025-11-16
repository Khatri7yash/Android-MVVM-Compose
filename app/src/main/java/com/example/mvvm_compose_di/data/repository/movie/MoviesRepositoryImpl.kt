package com.example.mvvm_compose_di.data.repository.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mvvm_compose_di.data.datasource.remote.APIServices
import com.example.mvvm_compose_di.data.datasource.remote.movies.NowPlayingMoviePagingDataSource
import com.example.mvvm_compose_di.data.model.MovieDetail
import com.example.mvvm_compose_di.data.model.MovieItem
import com.example.mvvm_compose_di.utils.networkutils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiService: APIServices
) : MoviesRepository {
    override fun getMovies(): Flow<PagingData<MovieItem>> =
        Pager(
            pagingSourceFactory = { NowPlayingMoviePagingDataSource(apiService) },
            config = PagingConfig(pageSize = 20)
        ).flow

    override suspend fun fetchMovieDetails(movieId: Int): Flow<DataState<MovieDetail>> = flow {
        emit(DataState.Loading)
        try {
            emit(DataState.Success(apiService.fetchMovieDetails(movieId)))
        }catch (e : Exception){
            e.printStackTrace()
            e.message?.let { emit(DataState.Error(it)) }
        }

    }
}
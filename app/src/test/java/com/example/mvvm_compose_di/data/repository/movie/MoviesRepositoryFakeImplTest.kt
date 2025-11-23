package com.example.mvvm_compose_di.data.repository.movie

import androidx.paging.PagingData
import com.example.mvvm_compose_di.data.model.MovieDetail
import com.example.mvvm_compose_di.data.model.MovieItem
import com.example.mvvm_compose_di.utils.networkutils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MoviesRepositoryFakeImplTest: MoviesRepository {

    private val moviesFlow = MutableStateFlow<PagingData<MovieItem>>(PagingData.empty())

    private val moviesList = ArrayList<MovieItem>()


    fun emitMovies(pagingData: PagingData<MovieItem>) {
        moviesFlow.value = pagingData
    }

    override fun getMovies(): Flow<PagingData<MovieItem>> {
        return flowOf(PagingData.from(moviesList))
    }


    override suspend fun fetchMovieDetails(movieId: Int): Flow<DataState<MovieDetail>> {
        return flowOf()
    }

    override suspend fun fetchRecommendedMovies(movieId: Int): Flow<DataState<List<MovieItem>>> {
        return flowOf()
    }

}
package com.example.mvvm_compose_di.data.repository.movie

import com.example.mvvm_compose_di.data.datasource.remote.APIServices
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiService: APIServices
) : MoviesRepository {
    override fun getMovies() {

    }
}
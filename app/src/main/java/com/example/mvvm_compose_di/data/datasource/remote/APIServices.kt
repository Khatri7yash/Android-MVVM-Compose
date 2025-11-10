package com.example.mvvm_compose_di.data.datasource.remote

import com.piashcse.hilt_mvvm_compose_movie.data.model.BaseModel
import com.piashcse.hilt_mvvm_compose_movie.data.model.MovieItem
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {


    @GET(ApiURL.MOVIES_ENDPOINT)
    fun getMovies(
        @Query("page") page: Int,
//        @Query("with_genres") genreId: String?
    ): BaseModel<MovieItem>
}
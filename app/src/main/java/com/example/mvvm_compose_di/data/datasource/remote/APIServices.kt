package com.example.mvvm_compose_di.data.datasource.remote


import com.example.mvvm_compose_di.data.model.BaseModel
import com.example.mvvm_compose_di.data.model.MovieItem
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {


    @GET(ApiURL.MOVIES_ENDPOINT)
    suspend fun getMovies(
        @Query("page") page: Int,
//        @Query("with_genres") genreId: String?
    ): BaseModel<MovieItem>
}
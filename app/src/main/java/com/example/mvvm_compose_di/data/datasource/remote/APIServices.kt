package com.example.mvvm_compose_di.data.datasource.remote


import com.example.mvvm_compose_di.data.model.BaseModel
import com.example.mvvm_compose_di.data.model.MovieDetail
import com.example.mvvm_compose_di.data.model.MovieItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIServices {


    @GET(ApiURL.MOVIES_ENDPOINT)
    suspend fun getMovies(
        @Query("page") page: Int,
//        @Query("with_genres") genreId: String?
    ): BaseModel<MovieItem>

    @GET(ApiURL.MOVIE_DETAILS_ENDPOINT)
    suspend fun fetchMovieDetails(
        @Path("movieId") movieId: Int
    ): MovieDetail
}
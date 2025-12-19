package com.example.mvvm_compose_di.data.datasource.remote

object ApiURL {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w342"
    const val IMAGE_URL_V2 = "https://image.tmdb.org/t/p/w780"


    const val MOVIES_ENDPOINT = "movie/now_playing"
    const val POPULAR_MOVIES_ENDPOINT = "movie/popular"
    const val MOVIE_DETAILS_ENDPOINT = "movie/{movieId}"
    const val RECOMMENDED_MOVIE_ENDPOINT = "movie/{movieId}/recommendations"
}
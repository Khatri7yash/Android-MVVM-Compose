package com.example.mvvm_compose_di.utils

import com.example.mvvm_compose_di.data.model.MovieItem

object FakeData {

    fun movie(
        id: Int = 1,
        title: String = "Inception",
        posterPath: String = "/poster.jpg",
        rating: Double = 8.8
    ) = MovieItem(
        id = id,
        title = title,
        posterPath = posterPath,
        genreIds = emptyList(),
        backdropPath = "",
        originalLanguage = "",
        originalTitle = "",
        releaseDate = "2010-07-16",
        overview = "A thief enters the dreams of others to steal their secrets.",
        popularity = 0.0,
        video = false,
        voteAverage = rating,
        voteCount = 0,
        adult = false
    )

    val fakeMovies = listOf(movie(id = 0),
        movie(id = 1),
        movie(id = 2),
        movie(id = 3),
        movie(id = 4))
}
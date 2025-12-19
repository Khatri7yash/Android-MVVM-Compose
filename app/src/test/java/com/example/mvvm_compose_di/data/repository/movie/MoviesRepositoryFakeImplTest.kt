package com.example.mvvm_compose_di.data.repository.movie

import com.example.mvvm_compose_di.data.datasource.remote.APIServices
import com.example.mvvm_compose_di.data.model.BaseModel
import com.example.mvvm_compose_di.utils.FakeData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.jupiter.api.Assertions.*

class MoviesRepositoryFakeImplTest/*: MoviesRepository*/ {


    private lateinit var repository: MoviesRepositoryImpl
    private val movieApi: APIServices = mockk()


    @Before
    fun setUp(){
        repository = MoviesRepositoryImpl(apiService = movieApi)
    }


    @org.junit.Test
    fun `getPopularMovies should return PagingData on successful API call`() = runTest{

//        ARRANGE
        val firstPage = BaseModel(
            page = 1,
            results = FakeData.fakeMovies,
            totalPages = 100,
            totalResults = 20
        )
        coEvery { movieApi.getPopularMovies(page = 1) } returns firstPage

//        ACT
        val pagingData = repository.getPopularMovies()


//        ASSERT
        assertNotNull(pagingData)

        val result = pagingData.first()
        assertNotNull(result)
    }


//    private val moviesFlow = MutableStateFlow<PagingData<MovieItem>>(PagingData.empty())
//
//    private val moviesList = ArrayList<MovieItem>()
//
//    fun emitMovies(pagingData: PagingData<MovieItem>) {
//        moviesFlow.value = pagingData
//    }
//
//    override fun getMovies(): Flow<PagingData<MovieItem>> {
//        return flowOf(PagingData.from(moviesList))
//    }
//
//    override fun getPopularMovies(): Flow<PagingData<MovieItem>> {
//        return flowOf(PagingData.from(moviesList))
//    }
//
//
//    override suspend fun fetchMovieDetails(movieId: Int): Flow<DataState<MovieDetail>> {
//        return flowOf()
//    }
//
//    override suspend fun fetchRecommendedMovies(movieId: Int): Flow<DataState<List<MovieItem>>> {
//        return flowOf()
//    }

}
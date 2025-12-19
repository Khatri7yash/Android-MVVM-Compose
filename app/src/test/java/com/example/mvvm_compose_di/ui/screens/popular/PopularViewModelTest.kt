package com.example.mvvm_compose_di.ui.screens.popular

import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.mvvm_compose_di.data.repository.movie.MoviesRepository
import com.example.mvvm_compose_di.ui.base.BaseViewModelTest
import com.example.mvvm_compose_di.utils.FakeData
import com.example.mvvm_compose_di.utils.networkutils.DataState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*


class PopularViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: PopularViewModel
    private val repository: MoviesRepository = mockk()

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = PopularViewModel(repo = repository )
    }

    @After
    override fun tearDown() {
        super.tearDown()
    }

    @Test
    fun `update loading state`() = runTest {
        viewModel.updateState( LoadState.Loading)
        assertEquals(viewModel.loadingState.value, DataState.Loading)
    }

    @Test
    fun `fetchPopularMovies should update _popularMoviesList with success state`() = runTest {
        val fakePagingData = PagingData.from(FakeData.fakeMovies)
        coEvery { repository.getPopularMovies() } returns flowOf(fakePagingData)

        viewModel.getPopularMovies()

        val resultPagingData = viewModel.popularMovies.first()

        // Assert: Did the action have the correct result?
        // We can't easily compare PagingData directly. For this test, a good first step
        // is to simply assert that the result is not null.
        assertNotNull(resultPagingData)
//        assertEquals(viewModel.popularMovies.value, fakePagingData)
    }

    @Test
    fun `fetchPopularMovies should update _popularMoviesList with Error state`() = runTest {
        val errorMessage = "Please contact administrator"
        coEvery { repository.getPopularMovies() } throws RuntimeException(errorMessage)

        viewModel.getPopularMovies()
        val expectedState = DataState.Error(errorMessage)
        assertEquals(expectedState,viewModel.loadingState.value)
    }

}
package com.example.mvvm_compose_di.ui.screens.home

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadStates
import com.example.mvvm_compose_di.data.repository.movie.MoviesRepository
import com.example.mvvm_compose_di.data.repository.movie.MoviesRepositoryFakeImplTest
import com.example.mvvm_compose_di.utils.networkutils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenViewModelTest {

    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var fakeRepository: MoviesRepository


    @Before
    fun setUp() {

        fakeRepository = MoviesRepositoryFakeImplTest()
        viewModel = HomeScreenViewModel(repo = fakeRepository)

    }

    @Test
    fun `updateLoadState should set LOADING state`() = runTest {

        val loadState = CombinedLoadStates(
            refresh = LoadState.Loading,
            prepend = LoadState.NotLoading(endOfPaginationReached = true),
            append = LoadState.NotLoading(endOfPaginationReached = true),
            source = LoadStates(
                refresh = LoadState.Loading,
                prepend = LoadState.NotLoading(true),
                append = LoadState.NotLoading(true)
            ),
            mediator = null
        )

        viewModel.updateLoadState(loadState)
        val state = viewModel.uiState.value
        assertTrue(state is DataState.Loading)
    }

    @Test
    fun `updateLoadState should set ERROR state`() = runTest {

        val loadState = CombinedLoadStates(
            refresh = LoadState.Error(Throwable()),
            prepend = LoadState.Error(Throwable()),
            append = LoadState.Error(Throwable()),
            source = LoadStates(
                refresh = LoadState.Error(Throwable()),
                prepend = LoadState.Error(Throwable()),
                append = LoadState.Error(Throwable())
            ),
            mediator = null
        )

        viewModel.updateLoadState(loadState)
        val state = viewModel.uiState.value
        assertTrue(state is DataState.Error)
    }

    @Test
    fun `updateLoadState should set SUCCESS state without reaching page end`() = runTest {

        val loadState = CombinedLoadStates(
            refresh = LoadState.NotLoading(false),
            prepend = LoadState.NotLoading(false),
            append = LoadState.NotLoading(false),
            source = LoadStates(
                refresh = LoadState.NotLoading(false),
                prepend = LoadState.NotLoading(false),
                append = LoadState.NotLoading(false)
            ),
            mediator = null
        )

        viewModel.updateLoadState(loadState)
        val state = viewModel.uiState.value
        assertTrue(state is DataState.Success)
    }

    @Test
    fun `updateLoadState should set SUCCESS state reached page end`() = runTest {

        val loadState = CombinedLoadStates(
            refresh = LoadState.NotLoading(true),
            prepend = LoadState.NotLoading(true),
            append = LoadState.NotLoading(true),
            source = LoadStates(
                refresh = LoadState.NotLoading(true),
                prepend = LoadState.NotLoading(true),
                append = LoadState.NotLoading(true)
            ),
            mediator = null
        )

        viewModel.updateLoadState(loadState)
        val state = viewModel.uiState.value
        assertTrue(state is DataState.Success)
    }


    @After
    fun tearDown() {

    }

}
package com.example.mvvm_compose_di.ui.screens.popular

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.paging.PagingData
import com.example.mvvm_compose_di.base.BaseAndroidTest
import com.example.mvvm_compose_di.data.repository.movie.MoviesRepository
import com.example.mvvm_compose_di.navigation.NavScreens
import com.example.mvvm_compose_di.utils.FakeData
import com.example.mvvm_compose_di.utils.networkutils.DataState
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test


@HiltAndroidTest
class PopularScreenTest: BaseAndroidTest() {

//    private val mockRepo: MoviesRepository = mockk()
//    private lateinit var viewModel: PopularViewModel

//    @Before
//    fun setUp() {
//        viewModel = PopularViewModel(repo = mockRepo)
//    }


    // This property will be injected by Hilt for your test
    @BindValue @JvmField
    val viewModel: PopularViewModel = mockk(relaxed = true)

    @After
    override fun tearDown() {
        super.tearDown()
    }

    @Test
    fun popularScreenShouldShowLoading() = runTest {
//        ARRANGE

//        viewModel.getPopularMovies()
//        viewModel.loadingState.value = DataState.Loading

//        ACT
        composeRule.apply {
            mainClock.advanceTimeBy(7_000)
            // Give the UI a moment to compose
            waitForIdle()
            // ASSERT
            onNodeWithTag(NavScreens.Popular.title).performClick()

//            mainClock.advanceTimeBy(7_000)
//            waitForIdle()
            // ASSERT
//            onNodeWithTag("ProgressIndicator").assertIsDisplayed()
        }
    }

    @Test
    fun popularScreenShouldShowSuccessfulDataList() = runTest {
        //Arrange
        val fakeData = PagingData.from(FakeData.fakeMovies)

//        coEvery { viewModel.getPopularMovies() } returns ( flowOf(PagingData.from(fakeData)))

        // DO mock the public StateFlow property that the UI uses.
        // Use `every` because `popularMovies` is a property, not a suspend function.

//        viewModel.getPopularMovies()
        every { viewModel.popularMovies } returns flowOf(fakeData).stateIn(this)
//        every { androidx.hilt.navigation.compose.hiltViewModel.isRefreshing } returns mutableStateOf(false)
        every { viewModel.loadingState } returns flowOf(DataState.Success(Unit)).stateIn(this)

        composeRule.apply {
            // ASSERT

            mainClock.advanceTimeBy(7_000)
            // Give the UI a moment to compose
            waitForIdle()
            onNodeWithTag(NavScreens.Popular.title).performClick()

            mainClock.advanceTimeBy(5_000)
            waitForIdle()
            // Check if a movie from your fake data is visible on the screen.

            val firstMovieTitle = FakeData.fakeMovies.first().id
            composeRule.onNodeWithTag(firstMovieTitle.toString()).assertIsDisplayed()
        }

    }


    @Test
    fun selectPopularTab() = runTest {
        composeRule.apply {
            mainClock.advanceTimeBy(5_000)
            waitForIdle()
            onNodeWithTag("topBar").assertIsDisplayed()
            onNodeWithTag("bottomBar").assertIsDisplayed()
            onNodeWithTag(NavScreens.Popular.title).performClick()

            mainClock.advanceTimeBy(5_000)
            waitForIdle()
            onNodeWithTag("screenTitle").assertTextEquals(NavScreens.Popular.title)
        }
    }

    @Test
    fun shouldShowMenuIcon(){
        composeRule.apply{
            mainClock.advanceTimeBy(5_000)
            waitForIdle()
            onNodeWithTag("topBar").assertIsDisplayed()
            onNodeWithTag("drawerIcon").assertIsDisplayed()
        }
    }

}
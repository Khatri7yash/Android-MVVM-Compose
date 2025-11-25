package com.example.mvvm_compose_di.ui.screens.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.onNodeWithTag
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mvvm_compose_di.data.model.MovieItem
import com.example.mvvm_compose_di.ui.BaseAndroidTest
import com.example.mvvm_compose_di.utils.networkutils.DataState
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test



@HiltAndroidTest
class HomeScreenUITest : BaseAndroidTest() {
    @Before
    override fun setup() {
        super.setup()
    }


    @Test
    fun loadingState_showsProgressIndicator(){
        composeRule.setContent {
            HomeContentScreen(
                title = "Movies",
                navigation = { _, _ -> },
                uiState = DataState.Loading,
                moviesItems = flowOf( PagingData.empty<MovieItem>()).collectAsLazyPagingItems()
            )
        }
        composeRule.onNode(isDialog()).assertDoesNotExist()
        composeRule.onNodeWithTag("ProgressIndicator").assertIsDisplayed()
    }

}
package com.example.mvvm_compose_di.ui.screens.popular

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.mvvm_compose_di.base.BaseAndroidTest
import com.example.mvvm_compose_di.navigation.NavScreens
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test


@HiltAndroidTest
class PopularScreenTest: BaseAndroidTest() {
    @Before
    fun setUp() {

    }

    @After
    override fun tearDown() {
        super.tearDown()
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
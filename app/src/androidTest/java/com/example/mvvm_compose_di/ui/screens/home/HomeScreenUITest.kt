package com.example.mvvm_compose_di.ui.screens.home

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.onNodeWithTag
import com.example.mvvm_compose_di.ui.BaseAndroidTest
import com.example.mvvm_compose_di.ui.component.Movies
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test



@HiltAndroidTest
class HomeScreenUITest : BaseAndroidTest() {

    @Before
    override fun setup() {
        super.setup()
//        hiltRule.inject()
    }


    @Test
    fun loadingState_showsProgressIndicator(){
        composeRule.activity.setContent {
           HomeScreen(navigation = {_,_ ->})
        }

        composeRule.waitUntil(timeoutMillis = 3000){
            composeRule.onNode(isDialog()).assertDoesNotExist()
            composeRule.onNodeWithTag("ProgressIndicator").assertIsDisplayed()
            false
        }
//        composeRule.onNode(isDialog()).assertDoesNotExist()
//        composeRule.onNodeWithTag("ProgressIndicator").assertIsDisplayed()

    }

}
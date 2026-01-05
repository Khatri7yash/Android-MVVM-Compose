package com.example.mvvm_compose_di.ui.screens.settings

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.mvvm_compose_di.base.BaseAndroidTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class SettingsScreenTest: BaseAndroidTest() {

    
    @Test
    fun openSettingScreen(){
        composeRule.apply{
            mainClock.advanceTimeBy(5_000)
            waitForIdle()
            onNodeWithTag("drawerIcon").performClick()
            onNodeWithTag("Settings").performClick()
            mainClock.advanceTimeBy(5_000)
            waitForIdle()
            onNodeWithTag("screenTitle").assertTextEquals("Settings")
        }
    }


    @Test
    fun openCameraScreen(){
        openSettingScreen()
        composeRule.apply {
            onNodeWithTag("CameraOption").assertIsDisplayed()
            onNodeWithTag("CameraOption").performClick()

            mainClock.advanceTimeBy(5_000)
            waitForIdle()

            onNodeWithTag("screenTitle").assertTextEquals("Scanner")
        }
    }

}
package com.example.mvvm_compose_di.ui.screens.scanner

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.mvvm_compose_di.base.BaseAndroidTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class ScannerScreenTest : BaseAndroidTest() {

    @Test
    fun openScannerScreen() {
        composeRule.apply {
            mainClock.advanceTimeBy(5_000)
            waitForIdle()
            onNodeWithTag("drawerIcon").performClick()
            onNodeWithTag("Settings").performClick()
            mainClock.advanceTimeBy(5_000)
            waitForIdle()
            onNodeWithTag("CameraOption").performClick()

            mainClock.advanceTimeBy(5_000)
            waitForIdle()

            onNodeWithTag("screenTitle").assertTextEquals("Scanner")
        }
    }

    @Test
    fun openCamera() {
        openScannerScreen()

        composeRule.apply {
            mainClock.advanceTimeBy(5_000)
            waitForIdle()
            
            onNodeWithTag("cameraPreview").assertIsDisplayed()
        }

    }


}
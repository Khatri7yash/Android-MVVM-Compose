package com.example.mvvm_compose_di.base

import androidx.compose.ui.test.junit4.createComposeRule
import com.example.mvvm_compose_di.utils.MainDispatcherRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
open class BaseAndroidTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()
//    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 2)
    val mainDispatcherRule = MainDispatcherRule()

    open fun setup() {
        hiltRule.inject()
    }


}
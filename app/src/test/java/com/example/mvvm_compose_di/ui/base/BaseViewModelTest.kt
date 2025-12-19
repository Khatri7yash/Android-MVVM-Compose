package com.example.mvvm_compose_di.ui.base

import com.example.mvvm_compose_di.utils.MainDispatcherRule
import org.junit.Rule

open class BaseViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    open fun setUp() {
    }

    open fun tearDown() {

    }

}
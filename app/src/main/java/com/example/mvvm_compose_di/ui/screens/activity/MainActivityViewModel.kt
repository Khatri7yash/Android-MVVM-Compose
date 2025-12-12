package com.example.mvvm_compose_di.ui.screens.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(): ViewModel(){
    private val _isLoading = MutableStateFlow(true)
    val isLoading get() = _isLoading.asStateFlow()

    val addFunction = ::addition
    val printFunction = ::printingResult

    init {
        viewModelScope.launch {
            delay(2000)
            _isLoading.value = false
        }

        printingResult(addFunction)
    }


    fun addition(a: Int, b: Int): Int{
        return (a + b)
    }

    fun printingResult(addFunction: (Int, Int) -> Int) {
        println("Result -> ${addFunction(10, 2)}")
    }


}
package com.example.extlib

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        normalConsumer()
    }
}

private suspend fun normalConsumer() = coroutineScope {
    val mutableSharedFlow = MutableSharedFlow<Int>()

    val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)


    launch {
        mutableSharedFlow
            .onStart {
                println("Started")
            }
            .collect {
                println("Receiver A: $it")
            }
    }

    launch {
        list
            .subList(0, 5)
            .forEach {
                println("Producing $it")
                mutableSharedFlow.emit(it)
                delay(1000)
            }
    }

    delay(2500)

    launch {
        mutableSharedFlow
            .onStart {
                println("Started")
            }
            .collect { collectedData ->
                println("Receiver B: $collectedData")
            }
    }

}
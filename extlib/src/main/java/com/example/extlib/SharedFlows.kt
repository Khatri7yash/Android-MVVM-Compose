package com.example.extlib

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
//        normalConsumer()
        consumerUsingSharedIn()
    }
}

private suspend fun consumerUsingSharedIn() = coroutineScope {
    launch {
        getSharedFlow().collect {
            println("Receiver A: $it")
        }
    }
}

private fun CoroutineScope.getSharedFlow(): SharedFlow<Int> {
    val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val sharedIn = flow<Int> {
        list
            .subList(0, 5)
            .forEach {
                println("Producing $it")
                emit(it)
                delay(1000)
            }
    }.shareIn(
        this,
        SharingStarted.WhileSubscribed(5000),
        0
    )
    return sharedIn
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
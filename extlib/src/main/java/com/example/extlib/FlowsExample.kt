package com.example.extlib

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    val data = producer()
    runBlocking {
//        data.collect {
//            println("Received: $it")
//        }
        coroutineScope {
            val job = launch {
                data.collect {
                    println("Received: $it")
                }
            }
            delay(3500) // In this example we are removing coroutine to remove collector to stop flows to understand cold flow mechanism (Flow will not execute until and unless it has collector)
            job.cancel()
        }

    }


}

fun producer() = flow<Int> {
    val list = listOf<Int>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("Produced")
    list.forEach {
        delay(1000)
        emit(it)

        println("Producing $it")
    }

}
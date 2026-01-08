package com.example.extlib

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


//YT Ref - https://www.youtube.com/watch?v=4xelRBv-aRE&list=PLRKyZvuMYSIPJ84lXQSHMn8P-0J8jW5YT&index=2

fun main() {
    runBlocking {
//        consumerWithJobCancel()
        multipleConsumerWithDiffTimeOfAttach()
//        multipleConsumerWithDiffTimeOfAttachCancellingB()
//        multipleConsumerWithDiffTimeOfAttachUsingAsync()
    }
}

suspend fun consumerWithJobCancel() =
    coroutineScope {
        val data = producer()
        val job = launch {
            data.collect {
                println("Received: $it")
            }
        }
        delay(3500) // In this example we are removing coroutine to remove collector to stop flows to understand cold flow mechanism (Flow will not execute until and unless it has collector)
        job.cancel()
    }


/**
 * Even if another consumer attached with flow after producer emits few data that newly attached consumer will get
 * whole data unlike hot flows(Channel)
 * */

suspend fun multipleConsumerWithDiffTimeOfAttach() = coroutineScope {
    val data = producer()
    launch {
        data.collect {
            println("Received Consumer - A: $it")
        }
    }

    launch {
        delay(2500)
        data.collect {
            println("Received Consumer - B: $it")
        }
    }
}

suspend fun multipleConsumerWithDiffTimeOfAttachCancellingB() = coroutineScope {
    val data = producer()
    val jobA = launch {
        data.collect {
            println("Received Consumer - A: $it")
        }
    }

    val jobB = launch {
        delay(2500)
        data.collect {
            println("Received Consumer - B: $it")
        }
    }

    delay(5000)
    jobB.cancel()


}

suspend fun multipleConsumerWithDiffTimeOfAttachUsingAsync() = coroutineScope {
    val data = producer()
    val deferredA = async {
        data.collect {
            println("Received Consumer - A: $it")
        }
    }

    val deferredB = async {
        delay(2500)
        data.collect {
            println("Received Consumer - B: $it")
        }
    }

    delay(3000)
    deferredA.await()

    delay(4000)
    deferredB.await()

    delay(6000)
    deferredA.cancel()


}

private fun producer() = flow<Int> {
    val list = listOf<Int>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("Produced")
    list.forEach {
        delay(1000)
        emit(it)

        println("Producing $it")
    }

}
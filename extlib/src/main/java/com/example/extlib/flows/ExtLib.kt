package com.example.extlib.flows


import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println("JVM module works")
        val extlib = ExtLib()
        extlib.start()
    }

}

open class ExtLib {

    private val channel = Channel<Int>()

    suspend fun start() = coroutineScope {
//        CoroutineScope(Dispatchers.IO).launch {
//        println("coroutine launched")
        launch {
            producer()
        }
        val job = launch {
            consumer()
        }

//        delay(5000)

//        job.cancel()

//        }
    }


    /**
     * Channels can have only single consumer at a time.
     * The first one attached will get all the emitted data
     * */
    private suspend fun consumer() {
        channel.consumeAsFlow().collect {
            println("Channel Flows -> $it")
        }

        channel.consumeEach { action -> println("Channel -> $action") }

//        println("Channel Other 1 -> " + channel.receive())
//        println("Channel Other 2 -> " + channel.receive())
//        println("Channel Other 3 -> " + channel.receive())

//        channel.consume { println("Channel -> $it") }
//        for (values in channel) {
//            println("Channel -> $values")
////            println("Channel Other -> " + channel.receive())
////            println("Channel Other -> " + channel.receive())
////            println("Channel Other -> " + channel.receive())
//        }
    }

    private suspend fun producer() {
        val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        list.forEach {
            delay(1000)
            channel.send(it)
        }
//        channel.send(1)
//        channel.send(2)
//        channel.send(3)
//        delay(3000)
//        channel.send(4)
        channel.close()
    }
}

//class ExtLib {
//
//
//
//}
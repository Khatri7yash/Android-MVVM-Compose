package com.example.extlib


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

    private val channel = kotlinx.coroutines.channels.Channel<Int>()

    suspend fun start() = coroutineScope {
//        CoroutineScope(Dispatchers.IO).launch {
//        println("coroutine launched")
        launch {
            producer()
        }
        launch {
            consumer()
        }
//        producer()
//        consumer()
//        }
    }

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
        channel.send(1)
        channel.send(2)
        channel.send(3)
        delay(3000)
        channel.send(4)
        channel.close()
    }
}

//class ExtLib {
//
//
//
//}
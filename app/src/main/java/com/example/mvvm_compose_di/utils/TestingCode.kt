package com.example.mvvm_compose_di.utils

import kotlinx.coroutines.launch


fun main() {
    ChannelExp()
}

open class ChannelExp {

    private val channel = kotlinx.coroutines.channels.Channel<Int>()

    constructor() {
        kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Default).launch {
            producer()
            consumer()
        }
    }

    private suspend fun consumer() {
        println(channel.receive())
    }

    private suspend fun producer() {
        channel.send(1)
        channel.send(2)
        channel.send(3)
    }
}
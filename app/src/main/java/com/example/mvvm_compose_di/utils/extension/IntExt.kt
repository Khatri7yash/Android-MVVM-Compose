package com.example.mvvm_compose_di.utils.extension

import kotlin.time.Duration.Companion.minutes

fun Int.hourMinutes(): String {
    return "${this.minutes.inWholeHours}h ${this % 60}m"
}
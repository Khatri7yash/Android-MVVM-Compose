package com.example.mvvm_compose_di.utils.extension

import kotlin.math.pow
import kotlin.math.roundToInt


fun Double.roundTo(numFractionDigits: Int): Double {
    val factor = 10.0.pow(numFractionDigits.toDouble())
    return (this * factor).roundToInt() / factor
}
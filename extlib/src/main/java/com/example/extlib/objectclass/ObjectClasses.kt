package com.example.extlib.objectclass


fun main() {
    Car.startEngine()
    Car.stopEngine()
    Car.drive()
    swapValuesWithoutThirdVariable()
}


fun swapValuesWithoutThirdVariable() {
    var a = 3
    var b = 5
    println("The value of a = $a and b = $b")
    a.let {
        a = b
        b = it
    }
    println("The value of a = $a and b = $b after swaping")
}

object Car : Motion {
    var isLeftHandDrive = false

    init {
        isLeftHandDrive = true
    }

    fun startEngine() {
        println("Engine Started")
    }

    fun stopEngine() {
        println("Engine Stopped")
    }

    fun drive() {
        println("Driving")
        moveForward()
        moveBackward()
    }

    override fun moveForward() {
        println("Moving Forward")
    }

    override fun moveBackward() {
        println("Moving Backward")
    }
}

interface Motion {
    fun moveForward()
    fun moveBackward()
}
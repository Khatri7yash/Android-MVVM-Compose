package com.example.extlib.objectclass


fun main() {
    Car.startEngine()
    Car.stopEngine()
    Car.drive()
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
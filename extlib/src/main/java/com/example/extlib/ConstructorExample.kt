package com.example.extlib


fun main() {
    val vehicle = Vehicle("BMW")
    vehicle.getName()
    vehicle.getWheelsCount()

    val vehicle2 = Vehicle(
        name = "Rikshaw",
        type = "Rikshaw",
        wheelsCount = 3,
        color = "Black",
        price = 200000.00
    )
    vehicle2.getName()
    vehicle2.getWheelsCount()
}


/**
 * First it will trigger primary constructor then init blocks and then secondary constructor
 * */
class Vehicle(
    var name: String,
    var type: String,
    var wheelsCount: Int,
    var color: String,
    var price: Double
) {


    init {
        println("Vehicle Color $color")
    }

    init {
        println("Vehicle's Price $price")
    }

    constructor(name: String) : this(
        name = name,
        type = "Default",
        wheelsCount = 0,
        color = "Default",
        price = 0.0
    ) {
        println("Type of vehicle $type")
    }

    fun getName() {
        println("Name of vehicle $name")
    }

    fun getWheelsCount() {
        println("Vehicle's wheel count is $wheelsCount")
    }

}
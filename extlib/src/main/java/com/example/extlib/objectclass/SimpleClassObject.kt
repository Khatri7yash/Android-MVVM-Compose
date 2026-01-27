package com.example.extlib.objectclass

fun main() {

    val person = Person()
    person.age = 30
    println("Person's Age = ${person.age}")


}

class Person {

    var age: Int = 20
        get() = field * 3
        set(value) {
            field = value * 2
        }

}
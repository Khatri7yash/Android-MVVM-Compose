package com.example.mvvm_compose_di.navigation


sealed class Screen(
    val route:String,
    vararg arguments: Any
) {

    object MainActivity : Screen("main")

}
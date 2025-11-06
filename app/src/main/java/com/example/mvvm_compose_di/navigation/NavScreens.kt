package com.example.mvvm_compose_di.navigation


private sealed class Screen(
    val route: String,
    vararg arguments: Any
) {

    object HomeScreen : Screen("home")

}

enum class NavScreens(val route: String, title: String) {
    HomeScreen("Home", "Home"),
//    PopBackStack("", "")
}
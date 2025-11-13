package com.example.mvvm_compose_di.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgs
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument


private sealed class Screen(
    val route: String,
    vararg arguments: Any
) {

    object HomeScreen : Screen("home")

}

enum class NavScreens(
    val route: String,
    val title: String,
    val argsName: Array<out NamedNavArgument> = emptyArray()
) {
    HomeScreen("Home", "Home"),
    MovieDetailsScreen(
        "MovieDetails",
        "Movie Details",
        argsName = arrayOf(navArgument("MovieDetails") {
            type = NavType.IntType
            nullable = false
            defaultValue = 0
        })
    ),
//    PopBackStack("", "")
}
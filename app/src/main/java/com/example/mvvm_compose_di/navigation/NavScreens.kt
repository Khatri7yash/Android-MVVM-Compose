package com.example.mvvm_compose_di.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
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
    val argsName: Array<out NamedNavArgument> = emptyArray(),
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            Icons.Filled.Home, contentDescription = "home"
        )
    }
) {
    HomeScreen("Home", "Home"),
    MovieDetailsScreen(
        "MovieDetails",
        "Movie Details",
        argsName = arrayOf(navArgument("/{movieItem}") {
            type = NavType.IntType
            nullable = false
            defaultValue = 0
        })
    ),
    SettingsScreen(route = "Settings", "Settings"),
    AllMovies(route = "AllMovies", "All", navIcon = {
        Icon(imageVector = Icons.Rounded.Home, contentDescription = "Home")
    }),
    Popular(route = "Popular", "Popular", navIcon = {
        Icon(imageVector = Icons.Rounded.List, contentDescription = "Popular")
    }),
    TopRated(route = "TopRated", "Top Rated", navIcon = {
        Icon(imageVector = Icons.Rounded.Star, contentDescription = "TopRated")
    }),
    UpComing(route = "UpComing", "Up Coming", navIcon = {
        Icon(imageVector = Icons.Rounded.KeyboardArrowUp, contentDescription = "UpComing")
    }),
    ScannerScreen(route = "Scanner", "Scanner"),
    ScannerScreen2(route = "Scanner2", "Scanner2")
//    PopBackStack("", "")
}
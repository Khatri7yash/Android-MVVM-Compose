package com.example.mvvm_compose_di.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mvvm_compose_di.ui.screens.home.HomeScreen
import com.example.mvvm_compose_di.ui.screens.movie_detail.MovieDetailsScreen
import com.example.mvvm_compose_di.ui.screens.settings.SettingsScreen

val LocalCurrentRoute = (compositionLocalOf<String?> { null })

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.substringBeforeLast("/")

    CompositionLocalProvider(LocalCurrentRoute provides currentRoute) {
        NavHost(
            navController = navController,
            startDestination = NavScreens.HomeScreen.route
        ) {
            NavScreens.entries.forEach { screens ->
                composable(
                    route = screens.route.plus(screens.argsName.joinToString { navArgument -> navArgument.name }),
                    arguments = screens.argsName.toList()
                ) { backStackEntry ->
                    when (screens) {
                        NavScreens.HomeScreen -> HomeScreen { navigation, args ->
                            navController navigateRoute (NavigationData(navigation, args))
                        }

                        NavScreens.SettingsScreen -> SettingsScreen { navigation, args ->
                            navController navigateRoute (NavigationData(navigation, args))
                        }

                        NavScreens.MovieDetailsScreen -> {
                            val key =
                                NavScreens.MovieDetailsScreen.argsName[0].name.substringAfter("{")
                                    .substringBefore("}")
                            val movieId = backStackEntry.arguments?.getString(key)?.toInt()
                            movieId?.let { id ->
                                MovieDetailsScreen(movieID = id) { navigation, args ->
                                    navController navigateRoute (NavigationData(navigation, args))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private infix fun NavHostController.navigateRoute(navigationPair: NavigationData) {
    val (navigation, args) = navigationPair
    navigation?.let {
        args?.let { array ->
            navigate(it.route + array.apply { joinToString(separator = "/", prefix = "/") })
        } ?: navigate(it.route)
    } ?: run { this.popBackStack() }
}
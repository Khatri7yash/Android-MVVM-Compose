package com.example.mvvm_compose_di.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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

val LocalCurrentRoute = (compositionLocalOf<String?> { null })

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.substringBeforeLast("/")

    CompositionLocalProvider(LocalCurrentRoute provides currentRoute) {
        NavHost(
            navController = navController,
            startDestination = NavScreens.HomeScreen.route.plus("/{}")
        ) {
            NavScreens.entries.forEach { screens ->
                composable(
                    route = screens.route.plus(
                        screens.argsName.joinToString(
                            prefix = "/{", separator = "}/{",
                            postfix = "}"
                        )
                    ),
                    arguments = screens.argsName.toList()
                ) {
                    when (screens) {
                        NavScreens.HomeScreen -> HomeScreen { navigation, args ->
                            navController navigateRoute (NavigationData(navigation, args))
                        }

                        NavScreens.MovieDetailsScreen -> MovieDetailsScreen { navigation, args ->
                            navController navigateRoute (NavigationData(navigation, args))
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
        this.navigate(it.route + args?.joinToString(separator = "/", prefix = "/"))
    } ?: run { this.popBackStack() }
}
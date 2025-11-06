package com.example.mvvm_compose_di.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mvvm_compose_di.ui.screens.home.HomeScreen

val LocalCurrentRoute = (compositionLocalOf<String?> { null })

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.substringBeforeLast("/")

    CompositionLocalProvider(LocalCurrentRoute provides currentRoute) {
        NavHost(navController = navController, startDestination = NavScreens.HomeScreen.route) {
            NavScreens.entries.forEach { screens ->
                composable(screens.route) {
                    when (screens) {
                        NavScreens.HomeScreen -> HomeScreen { navigation ->
                            navigation?.let {
                                navController.navigate(it.route)
                            } ?: run { navController.popBackStack() }

                        }
                    }
                }
            }
        }
    }
}
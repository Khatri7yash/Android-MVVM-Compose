package com.example.mvvm_compose_di.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() : NavController{

    val navController = rememberNavController()

//    NavHost(navController = navController, startDestination = )


    return navController
}
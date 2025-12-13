package com.example.mvvm_compose_di.ui.screens.popular

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.mvvm_compose_di.navigation.NavScreens
import com.example.mvvm_compose_di.ui.screens.base.BaseScreen


@Composable
fun PopularScreen(navigation: (NavScreens?, Array<out Any>?) -> Unit){
    val title by remember { mutableStateOf(NavScreens.Popular.title) }
    BaseScreen (
        title = title,
        navigation = navigation
    ){

    }

}
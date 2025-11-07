package com.example.mvvm_compose_di.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvm_compose_di.navigation.NavScreens
import com.example.mvvm_compose_di.ui.screens.base.BaseScreen

@Composable
fun HomeScreen(navigation: (NavScreens?) -> Unit) {

    val viewmodel = hiltViewModel<HomeScreenViewModel>()
    val title by remember { mutableStateOf("Movies") }

    LaunchedEffect(Unit) {
        viewmodel.loadGenres()
    }

    BaseScreen(
        title = title,
        navigation = navigation
    ) {
        Text(
            text = "Hello",
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow)
        )
    }
}
package com.example.mvvm_compose_di.ui.screens.movie_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mvvm_compose_di.navigation.NavScreens
import com.example.mvvm_compose_di.ui.component.Movies
import com.example.mvvm_compose_di.ui.component.base.BaseColumn


@Composable
fun MovieDetailsScreen(navigation: (NavScreens?, Array<out Any>?) -> Unit) {
    Column(Modifier
        .fillMaxSize()
        .background(Color.Red)) { }

}
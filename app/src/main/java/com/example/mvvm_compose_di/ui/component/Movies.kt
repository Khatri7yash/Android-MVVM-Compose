package com.example.mvvm_compose_di.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.LazyPagingItems
import com.example.mvvm_compose_di.data.model.MovieItem


@Composable
fun Movies(moviesItems: LazyPagingItems<MovieItem>) {
    Column(
        modifier = Modifier.background(Color.Blue)
    ) {
        DisplayMovie(moviesItems)
    }

}

fun DisplayMovie(moviesItems: LazyPagingItems<MovieItem>) {
//    LazyColumn() {
//        items(moviesItems.itemCount) {
//
//        }
//    }

}
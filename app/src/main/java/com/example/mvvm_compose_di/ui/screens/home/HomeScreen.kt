package com.example.mvvm_compose_di.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mvvm_compose_di.navigation.NavScreens
import com.example.mvvm_compose_di.ui.component.Movies
import com.example.mvvm_compose_di.ui.component.base.BaseColumn
import com.example.mvvm_compose_di.ui.screens.base.BaseScreen

@Composable
fun HomeScreen(navigation: (NavScreens?) -> Unit) {

    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val title by remember { mutableStateOf("Movies") }
    val moviesItems = viewModel.movies.collectAsLazyPagingItems()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(moviesItems.loadState) {
        viewModel.updateLoadState(moviesItems.loadState)
    }


//    LaunchedEffect(Unit) {
//        viewmodel.loadGenres()
//    }

    BaseScreen(
        title = title,
        navigation = navigation
    ) {
        BaseColumn(uiState, Modifier) {
            Movies(moviesItems, onclick = {})
        }
    }
}
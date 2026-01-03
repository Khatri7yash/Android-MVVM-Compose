package com.example.mvvm_compose_di.ui.screens.popular

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
fun PopularScreen(navigation: (NavScreens?, Array<out Any>?) -> Unit){
    val title by remember { mutableStateOf(NavScreens.Popular.title) }
    val viewModel = hiltViewModel<PopularViewModel>()
    val popularMovies = viewModel.popularMovies.collectAsLazyPagingItems()
    val loadingState by viewModel.loadingState.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.getPopularMovies()
    }

    LaunchedEffect(popularMovies.loadState) {
        viewModel.updateState(popularMovies.loadState.refresh)
    }

    BaseScreen (
        title = title,
        navigation = navigation
    ){
        BaseColumn(state = loadingState, modifier = Modifier ) {
            Movies(moviesItems = popularMovies){

            }
        }
    }

}
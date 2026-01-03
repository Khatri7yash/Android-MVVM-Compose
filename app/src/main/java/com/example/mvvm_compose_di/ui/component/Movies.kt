package com.example.mvvm_compose_di.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.mvvm_compose_di.data.datasource.remote.ApiURL
import com.example.mvvm_compose_di.data.model.MovieItem
import com.example.mvvm_compose_di.ui.theme.roundedShape
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin


@Composable
fun Movies(
    moviesItems: LazyPagingItems<MovieItem>,
    onclick: (MovieItem) -> Unit
) {
    Column(
        modifier = Modifier
    ) {
        DisplayMovie(
            moviesItems,
            onclick
        )
    }

}

@Composable
fun DisplayMovie(
    moviesItems: LazyPagingItems<MovieItem>,
    onclick: (MovieItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(horizontal = 5.dp)
    ) {
        items(moviesItems.itemCount) { index ->
            moviesItems[index]?.let {
                MovieCard(
                    modifier = Modifier
                        .height(250.dp)
                        .testTag(it.id.toString()),
                    movieItem = it,
                    onclick = onclick
                )
            }
        }
    }
}

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movieItem: MovieItem,
    onclick: (MovieItem) -> Unit
) {
    CoilImage(
        modifier = modifier
            .padding(5.dp)
            .clickable {
                onclick(movieItem)
            }
            .clip(RoundedCornerShape(10.dp))
            .then(modifier),
        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
        imageModel = { ApiURL.IMAGE_URL + movieItem.posterPath },
        loading = {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        failure = {
            Text(text = "image request failed.")
        },
        component = rememberImageComponent {
            +ShimmerPlugin(
                shimmer = Shimmer.Flash(
                    baseColor = MaterialTheme.colorScheme.onSecondary,
                    highlightColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    )
}
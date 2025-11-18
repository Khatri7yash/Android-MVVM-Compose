package com.example.mvvm_compose_di.ui.screens.movie_detail

import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvm_compose_di.data.datasource.remote.ApiURL
import com.example.mvvm_compose_di.data.model.Genre
import com.example.mvvm_compose_di.data.model.MovieDetail
import com.example.mvvm_compose_di.data.model.ProductionCompany
import com.example.mvvm_compose_di.data.model.ProductionCountry
import com.example.mvvm_compose_di.data.model.SpokenLanguage
import com.example.mvvm_compose_di.navigation.NavScreens
import com.example.mvvm_compose_di.ui.component.Movies
import com.example.mvvm_compose_di.ui.component.base.BaseColumn
import com.example.mvvm_compose_di.ui.component.text.ExpandableText
import com.example.mvvm_compose_di.ui.component.text.SubtitlePrimary
import com.example.mvvm_compose_di.ui.component.text.SubtitleSecondary
import com.example.mvvm_compose_di.ui.screens.base.BaseScreen
import com.example.mvvm_compose_di.ui.theme.subTitlePrimary
import com.example.mvvm_compose_di.utils.annotation.ThemePreview
import com.example.mvvm_compose_di.utils.extension.hourMinutes
import com.example.mvvm_compose_di.utils.extension.roundTo
import com.example.mvvm_compose_di.utils.networkutils.DataState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin


@Composable
fun MovieDetailsScreen(movieID: Int, navigation: (NavScreens?, Array<out Any>?) -> Unit) {

    val viewModel = hiltViewModel<MovieDetailViewModel>()
    val movieDetailsState by viewModel.movieDetail.collectAsState()

    LaunchedEffect(movieID) {
        viewModel.fetchMovieDetails(movieID)
    }

    MovieDetails(movieDetailsState, navigation)
}


@Composable
private fun MovieDetails(
    movieDetailsState: DataState<MovieDetail>,
    navigation: (NavScreens?, Array<out Any>?) -> Unit = { nav, arr ->
    }
) {

//    LaunchedEffect(movieDetailsState) {
//        if (movieDetailsState is DataState.Success)
//            println("PRINT_STATEMENT ---->>> ${movieDetailsState.data.id}")
//        else println("PRINT_STATEMENT ---->>> ${movieDetailsState.toString()}")
//    }

    val density = LocalDensity.current
    var bHeightPx by remember { mutableStateOf(0f) }
    var posterWidth by remember { mutableStateOf(0f) }
    val title by remember { mutableStateOf("Movie Details") }
    BaseScreen(
        title = title,
        navigation = navigation
    ) {
        BaseColumn(state = movieDetailsState) {
            if (movieDetailsState is DataState.Success) {
                val details = movieDetailsState.data
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                ) {

                    Box(
                        Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                    ) {
                        CoilImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(3f / 2f)
                                .graphicsLayer {
                                    alpha = 0.9f
                                    scaleX = 1f
                                    scaleY = 1f
                                    translationX = 0f
                                    translationY = 0f
                                    shadowElevation = 10f
                                    ambientShadowColor = Color.Black
                                    spotShadowColor = Color.Black
                                    renderEffect = BlurEffect(5f, 5f)
                                }
                                .onGloballyPositioned { coords ->
                                    bHeightPx = coords.boundsInParent().height
                                },
                            imageModel = { ApiURL.IMAGE_URL.plus(details.backdropPath) },
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.Crop
                            )
                        )
                        val bgHeight = with(density) { bHeightPx.toDp() }
                        Row(
                            Modifier
                                .offset { IntOffset(0, 0) }
                                .padding(start = 10.dp, top = max(0.dp, bgHeight - 50.dp))) {
                            CoilImage(
                                modifier = Modifier
                                    .size(135.dp, 180.dp) // Poster size (width x height)
                                    .clip(RoundedCornerShape(10.dp))
                                    .border(
                                        1.dp, Color.White, RoundedCornerShape(10.dp)
                                    )
                                    .onGloballyPositioned({ cord ->
                                        posterWidth = cord.size.width.toFloat()
                                    }),
                                imageModel = { ApiURL.IMAGE_URL.plus(details.posterPath) },
                                imageOptions = ImageOptions(
                                    contentScale = ContentScale.Crop,
                                ),
                                component = rememberImageComponent {
                                    +ShimmerPlugin(
                                        shimmer = Shimmer.Flash(
                                            baseColor = MaterialTheme.colorScheme.primary,
                                            highlightColor = MaterialTheme.colorScheme.onSecondary
                                        )
                                    )
                                },
                            )
                        }
                        val posterWidthDp = with(density) { posterWidth.toDp() }
                        val contentHeight = with(density) { (bHeightPx).toDp() }
                        Column(
                            Modifier
                                .offset {
                                    IntOffset(
                                        x = 0,
                                        y = 0
                                    )
                                }
                                .fillMaxWidth()
                                .padding(
                                    start = posterWidthDp + 20.dp,
                                    top = contentHeight + 5.dp
                                )) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .basicMarquee(
                                        iterations = Int.MAX_VALUE,
                                        animationMode = MarqueeAnimationMode.Immediately,
                                        velocity = 50.dp
                                    ),
                                text = details.title,
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1
                            )

                            Spacer(modifier = Modifier)

                            Row {
                                Column(
                                    Modifier.weight(1f),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    SubtitlePrimary(text = "Duration")
                                    SubtitleSecondary(text = details.runtime.hourMinutes())

                                    Spacer(Modifier.height(5.dp))

                                    SubtitlePrimary(text = "Language")
                                    SubtitleSecondary(text = details.originalLanguage)

                                }

                                Column(
                                    Modifier.weight(1f),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    SubtitlePrimary(text = "Release Date")
                                    SubtitleSecondary(text = details.runtime.hourMinutes())

                                    Spacer(Modifier.height(5.dp))

                                    SubtitlePrimary(text = "Rating")
                                    SubtitleSecondary(
                                        text = details.voteAverage.roundTo(2).toString()
                                    )

                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(15.dp))

                    Column(
                        Modifier
                            .padding(start = 10.dp, end = 10.dp)
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Description",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.height(10.dp))
                        ExpandableText(Modifier, text = details.overview, textModifier = Modifier)

                    }

                }
            }
        }
    }
}

@ThemePreview
//@Preview(name = "MovieDetail", showBackground = true)
@Composable
private fun PreviewDetailsScreen() {
    MovieDetails(DataState.Success(getFakeMovieDetails()))
}

private fun getFakeMovieDetails(): MovieDetail = MovieDetail(
    adult = false,
    backdropPath = "/hpXBJxLD2SEf8l2CspmSeiHrBKX.jpg",
    belongsToCollection = null,
    budget = 120_000_000,
    genres = listOf(
        Genre(18, "Drama"),
        Genre(27, "Horror"),
        Genre(14, "Fantasy")
    ),
    homepage = "https://www.frankensteingdt.com",
    id = 1062722,
    imdbId = "tt1312221",
    originCountry = listOf("US"),
    originalLanguage = "en",
    originalTitle = "Frankenstein",
    overview = "Dr. Victor Frankenstein, a brilliant but egotistical scientist, brings a creature to life in a monstrous experiment that ultimately leads to the undoing of both the creator and his tragic creation.",
    popularity = 841.4715,
    posterPath = "/g4JtvGlQO7DByTI6frUobqvSL3R.jpg",
    productionCompanies = listOf(
        ProductionCompany(
            id = 101082,
            logoPath = "/zjAYU6sUmYaeFeJ1yWeaqzsWAz8.png",
            name = "Double Dare You",
            originCountry = "US"
        ),
        ProductionCompany(
            id = 10678,
            logoPath = null,
            name = "Demilo Films",
            originCountry = "CA"
        ),
        ProductionCompany(
            id = 279898,
            logoPath = null,
            name = "Bluegrass 7",
            originCountry = "US"
        )
    ),
    productionCountries = listOf(
        ProductionCountry("US", "United States of America"),
        ProductionCountry("CA", "Canada")
    ),
    releaseDate = "2025-10-17",
    revenue = 144496,
    runtime = 150,
    spokenLanguages = listOf(
        SpokenLanguage("Danish", "da", "Dansk"),
        SpokenLanguage("English", "en", "English"),
        SpokenLanguage("French", "fr", "Français")
    ),
    status = "Released",
    tagline = "Only monsters play God.",
    title = "Frankenstein",
    video = false,
    voteAverage = 7.905,
    voteCount = 1160
)
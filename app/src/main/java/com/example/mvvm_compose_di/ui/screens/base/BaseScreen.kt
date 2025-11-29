package com.example.mvvm_compose_di.ui.screens.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mvvm_compose_di.R
import com.example.mvvm_compose_di.navigation.LocalCurrentRoute
import com.example.mvvm_compose_di.navigation.NavScreens
import com.example.mvvm_compose_di.ui.theme.MvvmComposeDiTheme
import com.example.mvvm_compose_di.ui.theme.redButtonColor
import com.example.mvvm_compose_di.utils.annotation.ThemePreview
import com.example.mvvm_compose_di.utils.networkutils.ConnectionState
import com.example.mvvm_compose_di.utils.networkutils.connectivityState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    title: String,
    navigation: (NavScreens?, Array<out Any>?) -> Unit,
    content: @Composable (() -> Unit)
) {
    val route = LocalCurrentRoute.current
    val connection by connectivityState()
    val isConnected = connection == ConnectionState.Available
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val drawerItems = listOf<String>("Profile", "Settings")

    MvvmComposeDiTheme {
        NavigationDrawer(scope, drawerState, drawerItems) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(text = title)
                        },
                        navigationIcon = {
                            when (route) {
                                NavScreens.HomeScreen.route -> {
                                    IconButton(onClick = {
                                        drawerState.apply {
                                            scope.launch {
                                                if (drawerState.isClosed) open() else close()
                                            }
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Rounded.Menu,
                                            contentDescription = "drawer icon"
                                        )
                                    }
                                }

                                else -> {
                                    IconButton(onClick = { navigation(null, null) }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Localized description"
                                        )
                                    }
                                }
                            }

                        },
                        actions = {})

                },
                bottomBar = {},
                snackbarHost = {
                    if (!isConnected) {
                        Snackbar(modifier = Modifier.padding(8.dp)) {
                            Text(stringResource(R.string.there_is_no_internet))
                        }
                    }
                }) { innerPadding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    color = MaterialTheme.colorScheme.background
                ) {
                    content()
                }
            }
        }
    }
}


@Composable
private fun NavigationDrawer(
    scope: CoroutineScope,
    drawerState: androidx.compose.material3.DrawerState,
    drawerItems: List<String>,
    content: @Composable () -> Unit
) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(5.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(10.dp)
                                .size(80.dp)
                                .clip(RoundedCornerShape(80.dp)),
                            painter = painterResource(R.drawable.profile_img),
                            contentDescription = "Profile Image",
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Hello Yash!",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            drawerItems.forEachIndexed { index, item ->
                                NavigationDrawerItem(
                                    label = { Text(text = item) },
                                    selected = index == selectedItem,
                                    onClick = {
                                        selectedItem = index
                                        scope.launch {
                                            drawerState.apply { close() }
                                        }
                                    },
                                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                )
                            }
                        }
                        Button(
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .fillMaxWidth(), colors = redButtonColor,
                            onClick = {
                                scope.launch {
                                    drawerState.apply { close() }
                                }
                            }) {
                            Text(text = "Logout")
                        }
                    }
                }

            }
        }) {
        content()
    }
}

@ThemePreview
@Composable
fun Preview() {
    BaseScreen(
        "Title",
        navigation = { _, _ -> }) {

    }
}
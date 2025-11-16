package com.example.mvvm_compose_di.ui.screens.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mvvm_compose_di.R
import com.example.mvvm_compose_di.navigation.LocalCurrentRoute
import com.example.mvvm_compose_di.navigation.NavScreens
import com.example.mvvm_compose_di.ui.theme.MvvmComposeDiTheme
import com.example.mvvm_compose_di.utils.networkutils.ConnectionState
import com.example.mvvm_compose_di.utils.networkutils.connectivityState

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

    MvvmComposeDiTheme {
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
                            NavScreens.HomeScreen.route -> {}
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
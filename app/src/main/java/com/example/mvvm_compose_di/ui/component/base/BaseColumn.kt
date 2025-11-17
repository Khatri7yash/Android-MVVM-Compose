package com.example.mvvm_compose_di.ui.component.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mvvm_compose_di.utils.networkutils.DataState


@Composable
fun <R> BaseColumn(
    state: DataState<R>,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var uiState by remember { mutableStateOf<DataState<R>>(state) }

    // Update errorState whenever errorMessage changes
    LaunchedEffect(state) {
        uiState = state
    }
    Column(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (uiState == DataState.Loading) Arrangement.Center else Arrangement.Top
    ) {
        when (uiState) {
            is DataState.Loading -> {
                CircularProgressIndicator()
            }

            is DataState.Success -> {
                content()
            }

            is DataState.Error -> {
                ErrorAlert(errorMessage = (uiState as DataState.Error).exceptionMessage) {
                    // Clear the error state when the "OK" button is clicked
                    uiState = DataState.Loading
                }
            }
        }
    }
}
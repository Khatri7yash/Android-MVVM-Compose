package com.example.mvvm_compose_di.ui.screens.base

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mvvm_compose_di.ui.theme.MvvmComposeDiTheme

@Composable
fun BaseScreen(content: @Composable (PaddingValues) -> Unit){
    MvvmComposeDiTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            content( innerPadding)
        }
    }
}
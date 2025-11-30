package com.example.mvvm_compose_di.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mvvm_compose_di.navigation.NavScreens
import com.example.mvvm_compose_di.ui.component.base.BaseColumn
import com.example.mvvm_compose_di.ui.component.text.SubtitlePrimary
import com.example.mvvm_compose_di.ui.screens.base.BaseScreen
import com.example.mvvm_compose_di.utils.networkutils.DataState


@Composable
fun SettingsScreen(navigation: (NavScreens?, Array<out Any>?) -> Unit) {

    val uiState by remember { mutableStateOf(DataState.Success("")) }

    BaseScreen(
        title = "Settings",
        navigation = navigation
    ) {
        BaseColumn<Any>(uiState, Modifier) {
            Column(
                Modifier
                    .background(Color.Green)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SubtitlePrimary(text = "Settings")
            }

        }
    }

}
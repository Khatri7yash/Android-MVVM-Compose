package com.example.mvvm_compose_di.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.mvvm_compose_di.navigation.NavScreens
import com.example.mvvm_compose_di.ui.component.base.BaseColumn
import com.example.mvvm_compose_di.ui.component.text.SubtitlePrimary
import com.example.mvvm_compose_di.ui.screens.base.BaseScreen
import com.example.mvvm_compose_di.utils.annotation.ThemePreview
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
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.fillMaxWidth().height(50.dp)
                    .testTag("CameraOption").clickable(onClick = {
                        navigation(NavScreens.ScannerScreen, null)
                    }),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Rounded.Star,
                        contentDescription = null)
                    Spacer(Modifier.width(20.dp))
                    Text(text = "Camera Scanner")
                }

                HorizontalDivider(thickness = 1.dp)

            }
        }
    }
}


@ThemePreview
@Composable
private fun Preview() {
    SettingsScreen{_,_->}
}
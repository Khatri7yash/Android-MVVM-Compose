package com.example.mvvm_compose_di.ui.screens.scanner

import androidx.compose.runtime.Composable
import com.example.mvvm_compose_di.navigation.NavScreens
import com.example.mvvm_compose_di.ui.screens.base.BaseScreen


@Composable
fun ScannerScreen(navigation: (NavScreens?, Array<out Any>?) -> Unit){

    BaseScreen(
        title = "Scanner",
        navigation = navigation
    ) {

    }

}
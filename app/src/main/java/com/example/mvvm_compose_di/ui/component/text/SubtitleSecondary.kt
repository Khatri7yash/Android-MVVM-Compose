package com.example.mvvm_compose_di.ui.component.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mvvm_compose_di.ui.theme.subTitleSecondary

@Composable
fun SubtitleSecondary(text:String) {
    Text(
        text = text,
        style = MaterialTheme.typography.subTitleSecondary,
        color = MaterialTheme.colorScheme.onSecondary,
    )
}
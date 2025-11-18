package com.example.mvvm_compose_di.ui.component.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.mvvm_compose_di.ui.theme.subTitlePrimary

@Composable
fun SubtitlePrimary(text: String, color: Color = MaterialTheme.colorScheme.onPrimary) {
    Text(
        text = text,
        style = MaterialTheme.typography.subTitlePrimary,
//        color = color,
    )
}
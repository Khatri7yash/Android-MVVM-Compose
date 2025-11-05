package com.example.mvvm_compose_di.ui.screens.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mvvm_compose_di.ui.screens.base.BaseScreen
import com.example.mvvm_compose_di.ui.theme.MvvmComposeDiTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val splashViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition { splashViewModel.isLoading.value }
        }
        enableEdgeToEdge()
        setContent {
            BaseScreen { innerPadding ->
                Text(
                    text = "Hello",
                    modifier = Modifier.padding(innerPadding)
                )
            }

        }
    }
}
package com.example.mvvm_compose_di.ui.screens.scanner

import android.graphics.Color
import android.widget.LinearLayout
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.mvvm_compose_di.navigation.NavScreens
import com.example.mvvm_compose_di.ui.screens.base.BaseScreen
import com.example.mvvm_compose_di.utils.annotation.ThemePreview


@Composable
fun ScannerScreen(navigation: (NavScreens?, Array<out Any>?) -> Unit) {
    BaseScreen(
        title = "Scanner",
        navigation = navigation
    ) {
        ScreenContent()
    }
}

@Composable
fun ScreenContent() {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember { LifecycleCameraController(context) }

    Box {
        CameraPreviewView(
            lifecycleOwner = lifecycleOwner,
            cameraController = cameraController
        )
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {

                cameraController.takePicture(
                    ContextCompat.getMainExecutor(context),
                    object :
                        ImageCapture.OnImageCapturedCallback() {
                        override fun onCaptureSuccess(image: ImageProxy) {
                            super.onCaptureSuccess(image)
                            image.close()
                        }

                        override fun onError(exception: ImageCaptureException) {
                            super.onError(exception)
                        }
                    })
            }) {
            Text(text = "Capture", color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun CameraPreviewView(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner,
    cameraController: LifecycleCameraController
) {

    AndroidView(
        modifier = modifier
            .testTag("cameraPreview")
            .fillMaxSize()
            .padding(10.dp),
        factory = { context ->
            PreviewView(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                setBackgroundColor(Color.BLACK)
                scaleType = PreviewView.ScaleType.FILL_CENTER
            }.also { previewView ->
                previewView.controller = cameraController
                cameraController.bindToLifecycle(lifecycleOwner)
            }
        }
    )
}

@Composable
@ThemePreview
fun PreviewScreen() {
    ScreenContent()
}
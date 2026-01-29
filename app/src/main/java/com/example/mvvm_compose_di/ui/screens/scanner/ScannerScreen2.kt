package com.example.mvvm_compose_di.ui.screens.scanner

import android.content.Context
import android.widget.LinearLayout
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.mvvm_compose_di.navigation.NavScreens
import com.example.mvvm_compose_di.ui.screens.base.BaseScreen
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


@Composable
fun ScannerScreen2(navigation: (NavScreens?, Array<out Any>?) -> Unit) {
    BaseScreen(
        title = "Scanner", navigation = navigation
    ) {
        ScreenContent()
    }
}


@Composable
private fun ScreenContent() {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember { LifecycleCameraController(context) }

    Box {
        val imageCapture = remember {
            ImageCapture.Builder().build()
        }
        CameraXPreview(imageCapture = imageCapture)
        Button(
            modifier = Modifier.align(Alignment.BottomCenter), onClick = {
                captureImage(imageCapture)
//                cameraController.takePicture(
//                    ContextCompat.getMainExecutor(context),
//                    object :
//                        ImageCapture.OnImageCapturedCallback() {
//                        override fun onCaptureSuccess(image: ImageProxy) {
//                            super.onCaptureSuccess(image)
//                            image.close()
//                        }
//
//                        override fun onError(exception: ImageCaptureException) {
//                            super.onError(exception)
//                        }
//                    })
            }) {
            Text(text = "Capture", color = MaterialTheme.colorScheme.primary)
        }
    }
}

private fun captureImage(imageCapture: ImageCapture) {
    val file = File.createTempFile("image", ".jpg")
    val outputFileOptions = ImageCapture.OutputFileOptions.Builder(file).build()
    imageCapture.takePicture(
        outputFileOptions,
        Executors.newSingleThreadExecutor(),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {

            }

            override fun onError(exception: ImageCaptureException) {

            }

        })
}

@Composable
fun CameraXPreview(
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
    previewScaleType: PreviewView.ScaleType = PreviewView.ScaleType.FIT_CENTER,
    imageCapture: ImageCapture
) {
    val mContext = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    var previewUseCase: Preview? = null

    AndroidView(modifier = Modifier, factory = { context ->
        PreviewView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(android.graphics.Color.BLACK)
            scaleType = previewScaleType
        }.also {
            previewUseCase = Preview.Builder().build()
            previewUseCase.surfaceProvider = it.surfaceProvider
        }
    })

    DisposableEffect(Unit) {
        var cameraProvider: ProcessCameraProvider? = null
        coroutineScope.launch {
            cameraProvider = mContext.cameraProvider()
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner, cameraSelector, previewUseCase, imageCapture
            )
        }
        onDispose {
            cameraProvider?.unbindAll()
        }
    }

}

suspend fun Context.cameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    val listenableFuture = ProcessCameraProvider.getInstance(this)
    listenableFuture.addListener({
        continuation.resume(listenableFuture.get())
    }, ContextCompat.getMainExecutor(this))
}
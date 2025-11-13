plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt.android)
//    alias(libs.plugins.google.services)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.example.mvvm_compose_di"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.mvvm_compose_di"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"59cd6896d8432f9c69aed9b86b9c2931\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Hilt Navigation
    implementation(libs.androidx.hilt.navigation.compose)

    // Splash Screen
    implementation(libs.androidx.core.splashscreen)

    // Retrofit
    implementation(libs.com.retrofit2)
    implementation(libs.gson.converter)

    // Gson
    implementation(libs.com.google.gson)

    // Room
    implementation(libs.androidx.room)

    // Navigation
    implementation(libs.androidx.navigation)

    //Paging
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)

    //Interceptor
    implementation(libs.logging.interceptor)
    implementation(libs.okHttp)

    // Image Loading
//    implementation(libs.landscapist.bom)
    implementation(libs.landscapist.coil)
    implementation(libs.landscapist.placeholder)
    implementation(libs.landscapist.animation)

}
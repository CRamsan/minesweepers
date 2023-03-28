plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
    id("io.gitlab.arturbosch.detekt")
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))

                implementation(AndroidX.activity.compose)
                implementation(AndroidX.appCompat)
                implementation(AndroidX.core.ktx)
            }
        }
    }
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "com.cramsan.minesweepers.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    namespace = "com.cramsan.minesweepers.android"
}

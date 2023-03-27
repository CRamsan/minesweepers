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

                implementation("androidx.activity:activity-compose:1.6.1")
                implementation("androidx.appcompat:appcompat:1.6.1")
                implementation("androidx.core:core-ktx:1.9.0")
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

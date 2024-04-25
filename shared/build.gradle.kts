@file:Suppress("OPT_IN_IS_NOT_ENABLED")

import de.fayard.refreshVersions.core.versionFor

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("io.gitlab.arturbosch.detekt")
}

version = "1.0-SNAPSHOT"

kotlin {
    androidTarget {

    }

    jvm {
        compilations.all {
            compilerOptions.configure {
                jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
            }
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
    }
}

android {
    compileSdk = 33

    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = versionFor("version.androidx.compose.compiler")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    namespace = "com.cramsan.minesweepers.common"
    packagingOptions {
        resources {
            excludes += "/META-INF/**"
        }
    }
}

dependencies {
    implementation(AndroidX.activity.compose)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.core.ktx)

    implementation(AndroidX.Compose.ui)
    implementation(AndroidX.Compose.foundation)
    implementation(AndroidX.Compose.ui.toolingPreview)
    debugImplementation(AndroidX.Compose.ui.tooling)
}

compose.resources {
    packageOfResClass = "shared"
}
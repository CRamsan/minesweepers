pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
    }

    plugins {
        val refreshVersion = extra["refresh.version"] as String

        kotlin("jvm")
        kotlin("multiplatform")
        kotlin("android")
        id("de.fayard.refreshVersions").version(refreshVersion)
        id("com.android.base")
        id("com.android.application")
        id("com.android.library")
        id("org.jetbrains.compose")
        id("io.gitlab.arturbosch.detekt")
        id("com.github.ben-manes.versions")
    }
}

plugins {
    id("de.fayard.refreshVersions")
}

rootProject.name = "Minesweepers"

include(":androidApp")
include(":shared")
include(":desktopApp")

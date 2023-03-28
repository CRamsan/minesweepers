pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
    }

    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        val agpVersion = extra["agp.version"] as String
        val composeVersion = extra["compose.version"] as String
        val detektVersion = extra["detekt.version"] as String
        val versionsVersion = extra["versions.version"] as String
        val refreshVersion = extra["refresh.version"] as String

        kotlin("jvm").version(kotlinVersion)
        kotlin("multiplatform").version(kotlinVersion)
        kotlin("android").version(kotlinVersion)
        id("com.android.base").version(agpVersion)
        id("com.android.application").version(agpVersion)
        id("com.android.library").version(agpVersion)
        id("org.jetbrains.compose").version(composeVersion)
        id("io.gitlab.arturbosch.detekt").version(detektVersion)
        id("com.github.ben-manes.versions").version(versionsVersion)
        id("de.fayard.refreshVersions").version(refreshVersion)
    }
}

plugins {
    id("de.fayard.refreshVersions")
}

rootProject.name = "Minesweepers"

include(":androidApp")
include(":shared")
include(":desktopApp")

buildscript {
    repositories {
        google()

    }
    dependencies {
        classpath(libs.navigation.safe.args.gradle.plugin)
        classpath(libs.mapsplatform.secrets.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
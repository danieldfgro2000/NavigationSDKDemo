import org.gradle.kotlin.dsl.libs

buildscript {
    repositories {
        google()

    }
    dependencies {
        classpath(libs.mapsplatform.secrets.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}

allprojects {
    configurations {
        all {
            exclude (group = "com.google.android.gms", module = "play-services-maps")
        }
    }
}
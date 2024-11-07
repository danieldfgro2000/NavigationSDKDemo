plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.navigationsdkdemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.navigationsdkdemo"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    dexOptions {
        javaMaxHeapSize = "4g"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            enableUnitTestCoverage = true
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // ayden
    api(libs.google.navigation)
    implementation(libs.ayden.checkout)
    implementation(libs.ayden.checkout.card)
    implementation(libs.ayden.checkout.components)
    implementation(libs.volley)

    // stripe
    implementation(libs.stripe)
    implementation(libs.stripe.android)

    implementation(project(":stripe"))
    implementation(libs.gson)
    implementation(libs.gson.converter)
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)

    testImplementation(libs.junit)
    testImplementation(libs.mockito.android)
    androidTestImplementation(libs.mockito.android)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    androidTestImplementation(libs.navigation.testing)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    annotationProcessor(libs.androidx.annotation)
    coreLibraryDesugaring(libs.desugar.jdk.libs.nio)
}

secrets {
    propertiesFileName = "secrets.properties"
    defaultPropertiesFileName  = "local.defaults.properties"
}

android.applicationVariants.all { variant ->
    android.defaultConfig.buildConfigField ("String", "SERVER_URL", "\"https://example.com\"")
    true
}
import java.text.SimpleDateFormat
import java.util.*

plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
}

val sdf = SimpleDateFormat("yyyy.M.dd")
val currentDate: String = sdf.format(Date())

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK
        versionCode = Config.VERSION_CODE
        versionName = currentDate

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = Config.java_version
        targetCompatibility = Config.java_version
    }

    kotlinOptions {
        jvmTarget = Config.JVM_TARGET
    }

    buildFeatures {
        android.buildFeatures.viewBinding = true
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    // ViewBindingPropertyDelegate
    implementation(ViewBindingDelegate.DELEGATE)

    // Kotlin
    implementation(Kotlin.CORE)
    implementation(Kotlin.STDLIB)

    // Design
    implementation(Design.APPCOMPAT)
    implementation(Design.MATERIAL)
    implementation(Design.CONSTRAINT_LAYOUT)
    implementation(Design.NAVIGATION_FRAGMENT)
    implementation(Design.NAVIGATION_UI_KTX)


    // Koin
    implementation(Koin.ANDROID)
    implementation(Koin.ANDROID_COMPAT)
    implementation(Koin.CORE)
    implementation(Koin.TEST)
    implementation(Koin.TEST_JUNIT4)

    // Retrofit
    implementation(Retrofit2.RETROFIT)
    implementation(Retrofit2.CONVERTER_JSON)
    implementation(Retrofit2.COROUTINES_ADAPTER)
    implementation(Retrofit2.LOGGING_INTERCEPTOR)

    //Tests
    testImplementation(Tests.JUNIT)
    androidTestImplementation(Tests.TEST_EXT_JUNIT)
    androidTestImplementation(Tests.ESPRESSO)
}
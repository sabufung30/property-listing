package com.sabufung.plugin

import com.android.build.gradle.BaseExtension
import com.sabufung.Config
import com.sabufung.utils.androidTestImplementation
import com.sabufung.utils.coreLibraryDesugaring
import com.sabufung.utils.debug
import com.sabufung.utils.implementation
import com.sabufung.utils.kapt
import com.sabufung.utils.kaptAndroidTest
import com.sabufung.utils.kaptTest
import com.sabufung.utils.release
import com.sabufung.utils.testImplementation


//
// Common logic for all Android modules.
// You have to apply an Android plugin beforehand (library, application, etc).
//

apply(plugin = "kotlin-android")
apply(plugin = "kotlin-kapt")
apply(plugin = "kotlin-parcelize")

// https://www.lordcodes.com/posts/testing-on-android-using-junit-5
// TODO: NoSuchMethodError: com.android.build.gradle.internal.variant.BaseVariantData.getScope()Lcom/android/build/gradle/internal/scope/VariantScope;
//apply(plugin = "de.mannodermaus.android-junit5")

configure<BaseExtension> {
    setCompileSdkVersion(Config.compileSdkVersion)

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion

        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packagingOptions {
        excludes.apply {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
        }
        create("dev") {
            isDebuggable = when (project.findProperty("gppTrack")) {
                null -> true  // Enable for UI tests purpose.
                else -> false // Disable for Play Store publishing.
            }
            isMinifyEnabled = true
        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.all {
            it.maxHeapSize = "1024m"
        }
    }
}

dependencies {
    // Flag to enable support for the new language APIs
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:_")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:_")
    implementation("androidx.fragment:fragment:_")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:_")
    kapt("com.google.dagger:hilt-android-compiler:_")

    val hiltTesting =  "com.google.dagger:hilt-android-testing:_"
    androidTestImplementation(hiltTesting)
    kaptAndroidTest("com.google.dagger:hilt-compiler:_")
    testImplementation(hiltTesting)
    kaptTest("com.google.dagger:hilt-compiler:_")

    implementation("androidx.hilt:hilt-work:_")
    kapt("androidx.hilt:hilt-compiler:_")

    testImplementation("org.robolectric:robolectric:_") {
        exclude(group = "com.google.protobuf", module = "protobuf-java")
    }

    testImplementation("androidx.navigation:navigation-testing:_")
}

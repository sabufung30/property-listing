package com.sabufung.plugin

import com.sabufung.utils.androidTestImplementation
import com.sabufung.utils.implementation
import com.sabufung.utils.kapt
import com.sabufung.utils.testImplementation
import com.sabufung.utils.devImplementation
import com.sabufung.utils.debugImplementation

//
// Common logic for all Android feature modules.
//

plugins {
    id("com.sabufung.plugin.android-library")
}

configure<com.android.build.gradle.LibraryExtension> {
    sourceSets {
        get("test").java.srcDir("$projectDir/src/testFixtures/java")
        get("androidTest").java.srcDir("$projectDir/src/testFixtures/java")
    }
    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    // All feature modules are depending on base module.
    implementation(project(":domains:navigation"))
//    implementation(project(":core_app_data"))
    implementation(project(":core:ui"))
//    implementation(project(":core_feature_flags"))
//    implementation(project(":core_logger_ui"))

//    testImplementation(project(":core_logger_protobuf_test"))
//    implementation(project(":core_logger_protobuf"))

    implementation("com.squareup.moshi:moshi:_")
    implementation("com.squareup.moshi:moshi-kotlin:_")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:_")

    testImplementation("androidx.navigation:navigation-testing:_")
    testImplementation("androidx.compose.ui:ui-test:_")
    testImplementation("androidx.compose.ui:ui-test-junit4:_")
    androidTestImplementation("androidx.compose.ui:ui-test:_")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:_")

    testImplementation("androidx.test:rules:_")
    androidTestImplementation("androidx.test:rules:_")
}

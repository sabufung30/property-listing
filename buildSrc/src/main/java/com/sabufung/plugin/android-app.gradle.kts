package com.sabufung.plugin

import com.android.build.gradle.AppExtension
import com.sabufung.Config
import com.sabufung.utils.androidTestImplementation
import com.sabufung.utils.debug
import com.sabufung.utils.dev
import com.sabufung.utils.release
import com.sabufung.utils.testImplementation

apply(plugin = "com.android.application")
apply(plugin = "com.sabufung.plugin.android-base")
apply(plugin = "com.sabufung.plugin.android-lint")

val appId = project.name.let { it.subSequence("app_".length, it.length) }

val releaseSigningConfigPassword: String? = System.getenv("RELEASE_UPLOAD_KEY_PASSWORD")

configure<AppExtension> {
    defaultConfig {
        setApplicationId("${Config.baseApplicationId}.$appId")
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

/**
 * Make sure androidTestImplementation and testImplementation are synced so tests can be run
 * on both instrumented and unit testing environment
 *
 * TODO Refactor TestLibs in a single project
 */
dependencies {
    // Foundations
    testImplementation("androidx.test:core-ktx:_")
    testImplementation("androidx.test.ext:junit:_")
    testImplementation("androidx.test:runner:_")
    testImplementation("androidx.test:rules:_")

    // Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:_") {
        exclude(group = "org.checkerframework", module = "checker")
    }
    androidTestImplementation("androidx.test.espresso:espresso-contrib:_") {
        exclude(group = "org.checkerframework", module = "checker")
    }
    androidTestImplementation("androidx.test.espresso:espresso-intents:_") {
        exclude(group = "org.checkerframework", module = "checker")
    }
    androidTestImplementation("androidx.test.espresso:espresso-web:_") {
        exclude(group = "org.checkerframework", module = "checker")
    }
    androidTestImplementation("androidx.test.espresso:espresso-idling-resource:_") {
        exclude(group = "org.checkerframework", module = "checker")
    }

    // Mocks
    testImplementation("org.mockito:mockito-android:_")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:_") {
        exclude("org.mockito")
    }

    testImplementation("androidx.navigation:navigation-testing:_")
    testImplementation("androidx.compose.ui:ui-test:_")
    testImplementation("androidx.compose.ui:ui-test-junit4:_")
    testImplementation("androidx.arch.core:core-testing:_")

    // Foundations
    androidTestImplementation("androidx.test:core-ktx:_")
    androidTestImplementation("androidx.test.ext:junit:_")
    androidTestImplementation("androidx.test:runner:_")
    androidTestImplementation("androidx.test:rules:_")

    // Mocks
    androidTestImplementation("org.mockito:mockito-android:_")
    androidTestImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:_") {
        exclude("org.mockito")
    }

    androidTestImplementation("androidx.navigation:navigation-testing:_")
    androidTestImplementation("androidx.compose.ui:ui-test:_")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:_")
    androidTestImplementation("androidx.arch.core:core-testing:_")

}

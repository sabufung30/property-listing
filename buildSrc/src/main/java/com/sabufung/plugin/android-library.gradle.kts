package com.sabufung.plugin

import com.sabufung.utils.androidTestImplementation
import com.sabufung.utils.debugImplementation
import com.sabufung.utils.devImplementation
import com.sabufung.utils.testImplementation

apply(plugin = "com.android.library")
apply(plugin = "com.sabufung.plugin.android-base")

configure<com.android.build.gradle.LibraryExtension> {
    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    testImplementation("androidx.test.ext:junit:_")
    androidTestImplementation("androidx.test.ext:junit:_")

    // https://developer.android.com/training/basics/fragments/testing
    // fragment-testing has to be used as an app dependency, or their Activity will failed to be processed:
    // https://github.com/android/android-test/issues/294#issuecomment-694683850
    val fragmentTesting = "androidx.fragment:fragment-testing:_"
    debugImplementation(fragmentTesting)
    devImplementation(fragmentTesting)
}

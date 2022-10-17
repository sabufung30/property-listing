package com.sabufung.plugin

import com.android.build.api.dsl.BuildFeatures
import com.android.build.api.dsl.ComposeOptions
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.sabufung.Versions
import com.sabufung.utils.debugImplementation
import com.sabufung.utils.implementation
import com.sabufung.utils.testImplementation

val commonBuildFeatures: BuildFeatures.() -> Unit = {
    compose = true
}

val commonComposeOptions: ComposeOptions.() -> Unit = {
    kotlinCompilerExtensionVersion = Versions["version.androidx.compose.compiler"]
}

convention.apply {
    findByType<LibraryExtension>()?.apply {
        buildFeatures(commonBuildFeatures)
        composeOptions(commonComposeOptions)
    }?: findByType<BaseAppModuleExtension>()?.apply {
        buildFeatures(commonBuildFeatures)
        composeOptions(commonComposeOptions)
    }
}

dependencies {
    implementation("androidx.compose.compiler:compiler:_")
    implementation("androidx.compose.runtime:runtime:_")
    implementation("androidx.compose.animation:animation:_")
    implementation("androidx.compose.ui:ui:_")
    implementation("androidx.compose.ui:ui-util:_")
    implementation("androidx.compose.foundation:foundation:_")
    implementation("androidx.compose.foundation:foundation-layout:_")
    implementation("androidx.compose.material:material:_")
    implementation("androidx.compose.runtime:runtime-livedata:_")
    implementation("androidx.compose.ui:ui-tooling:_")
    implementation("androidx.compose.ui:ui-tooling-preview:_")
    // Required for Compose Layout Inspector
    debugImplementation("org.jetbrains.kotlin:kotlin-reflect:${Versions["version.kotlin"]}")

    implementation("androidx.paging:paging-compose:_")
    implementation("androidx.activity:activity-compose:_")
    implementation("androidx.constraintlayout:constraintlayout-compose:_")
    implementation("androidx.hilt:hilt-navigation-compose:_")
    implementation("androidx.navigation:navigation-compose:_")

    testImplementation("androidx.compose.ui:ui-test:_")
    testImplementation("androidx.compose.ui:ui-test-junit4:_")
    testImplementation("androidx.compose.ui:ui-test-manifest:_")

}

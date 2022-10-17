package com.sabufung.plugin

import com.android.build.gradle.BaseExtension
import com.sabufung.utils.implementation
import com.sabufung.utils.kapt
import com.sabufung.utils.testImplementation

configure<BaseExtension> {
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments(mapOf(
                    "room.incremental" to "true",
                ))
            }
        }
    }
}

dependencies {
    implementation("androidx.room:room-runtime:_")
    implementation("androidx.room:room-ktx:_")
    testImplementation("androidx.room:room-testing:_")
    kapt("androidx.room:room-compiler:_")
}

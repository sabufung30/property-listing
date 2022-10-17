package com.sabufung

import java.io.File
import com.sabufung.utils.SafeProperties
import com.sabufung.utils.toSafeProperties

object Config {
    private val properties: SafeProperties = File("gradle.properties").toSafeProperties()
    private val ciVersions: SafeProperties? = File("ci_version.properties")
        .takeIf { it.exists() }
        ?.toSafeProperties()

    val baseApplicationId = properties["android.config.baseApplicationId"]

    val compileSdkVersion = properties.getInt("android.config.compileSdkVersion")
    val minSdkVersion = properties.getInt("android.config.minSdkVersion")
    val targetSdkVersion = properties.getInt("android.config.maxSdkVersion")

    val versionCode: Int = (ciVersions ?: properties).getInt("android.config.version_code")
    val versionName: String = (ciVersions ?: properties)["android.config.version_name"]
}
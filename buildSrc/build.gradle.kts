repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

val versions =
    org.jetbrains.kotlin.konan.properties.loadProperties(projectDir.resolve("../versions.properties")
        .toString())

dependencies {
    /** Adds a refreshVersion handled implementation dependency */
    val refVerImpl: (String, String) -> Unit = { artifact, version ->
        implementation("$artifact:${versions["version.$version"]}")
    }

    implementation(gradleApi())
    implementation("com.android.tools.build:gradle:${versions["plugin.android"]}")
    refVerImpl("org.jetbrains.kotlin:kotlin-gradle-plugin", "kotlin")
    refVerImpl("com.google.dagger:hilt-android-gradle-plugin", "google.dagger")
}
@Suppress("GradlePluginVersion") buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies { // Allowing to populate versions.properties, used in buildSrc
        classpath("com.android.tools.build:gradle:_")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.9.1")
    }
}

plugins {
    /**
     * Official workarounds for fixing build cache issues on android
     * (https://github.com/gradle/android-cache-fix-gradle-plugin)
     */
    // plugin versions are defined via refreshVersions in versions.properties
    id("org.gradle.android.cache-fix") apply false
}

subprojects {
    apply(plugin = "org.gradle.android.cache-fix")
}

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}


package com.sabufung.plugin

import com.android.build.gradle.AppExtension

// Highly inspired by:
// https://proandroiddev.com/an-unofficial-guide-to-android-lint-188c0654b29b
configure<AppExtension> {
    lintOptions {
        // TODO temporary disable until issue is fixed
        // https://issuetracker.google.com/issues/178631052
        isAbortOnError = false
        isCheckTestSources = true
        isCheckGeneratedSources = true
        lintConfig = file("lint.xml")
        // Define reports
        xmlReport = true
        htmlReport = true
        textReport = false
        // Consider an app module also checking its dependencies.
        isCheckDependencies = true
        // Save some time during release builds, because
        // it's automatically running by default.
        isCheckReleaseBuilds = false
        // Cannot be ignored with baseline
        disable("ObsoleteLintCustomCheck")
        disable("SuspiciousImport")
        disable("ExifInterface")
    }
}

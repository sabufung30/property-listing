import com.sabufung.utils.ignoreCommonPackagingOptions

plugins {
    id("com.sabufung.plugin.android-app")
    id("com.sabufung.plugin.compose")
    id("com.sabufung.plugin.room")
    id("dagger.hilt.android.plugin")
}

android {
    defaultConfig {
        buildFeatures {
            buildConfig = true
        }
    }
}

dependencies {
    // Core module
    implementation(project(":core:network"))
    implementation(project(":core:ds_res"))
    implementation(project(":core:ds_theme"))
    implementation(project(":core:ds_component"))
    implementation(project(":core:ui"))
    implementation(project(":domains:property_item"))

    // Domain module
    implementation(project(":domains:navigation"))
    implementation(project(":domains:custom_tabs"))

    // Feature module
    implementation(project(":features:home"))
    implementation(project(":features:profile"))

    implementation(AndroidX.camera.core)
    implementation(AndroidX.camera.camera2)
    implementation(AndroidX.camera.lifecycle)
    implementation(AndroidX.camera.view)
    implementation(AndroidX.camera.extensions)
    implementation(AndroidX.dataStore.preferences)
    implementation(AndroidX.core.splashscreen)

    implementation(KotlinX.coroutines.core)

    implementation(AndroidX.core.ktx)
    implementation(AndroidX.activityKtx)
    implementation(AndroidX.appCompat)

    implementation(KotlinX.coroutines.core)
    implementation(AndroidX.preferenceKtx)

    implementation(AndroidX.lifecycle.liveDataKtx)
    implementation(AndroidX.lifecycle.runtimeKtx)
    implementation(AndroidX.lifecycle.process)

    implementation(AndroidX.work.runtimeKtx)

    implementation(COIL.compose)
}
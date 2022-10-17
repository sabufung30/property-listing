import com.sabufung.utils.implementation

plugins {
    id("com.sabufung.plugin.android-library")
    id("com.sabufung.plugin.android-domain")
    id("com.sabufung.plugin.compose")
}

dependencies {
    implementation(project(":core:ui"))

    implementation(AndroidX.core.ktx)
    implementation(AndroidX.appCompat)
    implementation(Square.moshi)
    implementation(Square.moshi.kotlinReflect)

    api(AndroidX.navigation.fragmentKtx)
    api(AndroidX.navigation.compose)
}

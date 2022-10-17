plugins {
    id("com.sabufung.plugin.android-library")
    id("com.sabufung.plugin.compose")
}

dependencies {
    implementation(project(":core:ds_theme"))
    implementation(project(":core:ds_component"))
    implementation(project(":core:network"))

    implementation(Google.accompanist.insets)
    api(Google.accompanist.swiperefresh)
}

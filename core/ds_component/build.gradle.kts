plugins {
    id("com.sabufung.plugin.android-library")
    id("com.sabufung.plugin.compose")
}

dependencies {

    implementation(project(":core:ds_theme"))

    implementation(AndroidX.core.ktx)
    implementation(AndroidX.appCompat)
    implementation(COIL.compose)
    implementation(Google.accompanist.flowlayout)
    api(Google.accompanist.pager)
    api(Google.accompanist.pager.indicators)
    api(Google.accompanist.swiperefresh)
    api(JakeWharton.timber)
}

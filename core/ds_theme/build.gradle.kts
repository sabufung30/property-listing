plugins {
    id("com.sabufung.plugin.android-library")
    id("com.sabufung.plugin.compose")
}

dependencies {
    implementation(project(":core:ds_res"))

    implementation(Google.accompanist.systemuicontroller)
}

import com.sabufung.utils.implementation

plugins {
    id("com.sabufung.plugin.android-feature")
    id("com.sabufung.plugin.compose")
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
    namespace = "com.sabufung.feature.home"
    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}


dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:ds_theme"))
    implementation(project(":core:ds_res"))
    implementation(project(":core:ds_component"))
    implementation(project(":core:ui"))

    implementation(project(":domains:property_item"))

    implementation(Square.okHttp3.okHttp)
    implementation(Square.retrofit2.converter.moshi)

    implementation(AndroidX.core.ktx)
    implementation(AndroidX.activityKtx)
    implementation(AndroidX.appCompat)

    implementation(KotlinX.coroutines.core)
    implementation(AndroidX.dataStore.preferences)

    implementation(JakeWharton.timber)
    implementation("com.github.bumptech.glide:glide:_")
    implementation(AndroidX.paging.runtime)
}

import com.sabufung.utils.implementation

plugins {
    id("com.sabufung.plugin.android-library")
    id("com.sabufung.plugin.android-domain")
    id("com.sabufung.plugin.compose")
    id("com.sabufung.plugin.room")
    id("com.google.protobuf")
}

android {
    buildFeatures {
        buildConfig = true
    }
}


protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.7"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                val java by registering {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:ds_res"))
    implementation(project(":core:ds_theme"))
    implementation(project(":core:ds_component"))
    implementation(project(":core:ui"))

    implementation(project(":domains:navigation"))

    implementation(Firebase.analyticsKtx)
    implementation(Firebase.crashlyticsKtx)
    implementation(JakeWharton.timber)
    implementation(Square.okHttp3.okHttp)
    implementation(Square.retrofit2.converter.moshi)

    implementation(Square.moshi)
    implementation(Square.moshi.kotlinReflect)

    implementation(AndroidX.core.ktx)
    implementation(AndroidX.activityKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.dataStore.preferences)

    implementation(KotlinX.coroutines.core)
    implementation(AndroidX.preferenceKtx)

    implementation(AndroidX.work.runtimeKtx)
    implementation("com.google.protobuf:protobuf-java:_")

    debugImplementation(Square.okHttp3.loggingInterceptor)
    devImplementation(Square.okHttp3.loggingInterceptor)
}

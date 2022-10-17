
import com.android.build.api.dsl.LibraryBuildType
import com.sabufung.utils.api
import com.sabufung.utils.debug
import com.sabufung.utils.dev
import com.sabufung.utils.implementation

plugins {
    id("com.sabufung.plugin.android-library")
    id("com.sabufung.plugin.room")
}

android {
    buildFeatures {
        buildConfig = true
    }

    sourceSets {
        debug {
            java.srcDirs("src/internal/java")
            res.srcDirs("src/internal/res")
        }
        dev {
            java.srcDirs("src/internal/java")
            res.srcDirs("src/internal/res")
        }
    }

    buildTypes {
        val buildConfigProperty = { name: String ->
            (System.getenv(name) ?: project.findProperty(name)).let {
                if (it == null) "null" else "\"$it\""
            }
        }
        val addStringBuildConfigFields: LibraryBuildType.(List<String>) -> Unit = { keys ->
            keys.forEach { key ->
                val value = buildConfigProperty(key)
                buildConfigField("String", key, value)
            }
        }
        val androidTestsBuildConfigKeys = listOf(
            "X_AUTOMATION",
            "TRUE_CLIENT_IP"
        )

        dev {
            addStringBuildConfigFields(androidTestsBuildConfigKeys)
        }

        debug {
            addStringBuildConfigFields(androidTestsBuildConfigKeys)
        }
    }
}

dependencies {

    implementation(Square.okHttp3.okHttp)
    implementation(AndroidX.annotation)
    implementation(KotlinX.coroutines.core)
    implementation(KotlinX.coroutines.android)
    implementation(Square.retrofit2.retrofit)
    implementation(Square.retrofit2.converter.scalars)
    implementation("com.squareup.retrofit2:converter-protobuf:_") {
        exclude(group = "com.google.protobuf", module = "protobuf-java")
    }
    implementation(Square.retrofit2.converter.moshi)

    implementation(AndroidX.core.ktx)

    implementation(Square.moshi)
    implementation(Square.moshi.kotlinReflect)
    kapt(Square.moshi.kotlinCodegen)

    implementation(AndroidX.paging.runtime)

    implementation(platform(Firebase.bom))
    implementation(Firebase.authenticationKtx)
    implementation(Firebase.analyticsKtx)
    api(JakeWharton.timber)

    debugImplementation(Square.okHttp3.loggingInterceptor)
    devImplementation(Square.okHttp3.loggingInterceptor)

    debugImplementation(Chucker.library)
    devImplementation(Chucker.library)

}

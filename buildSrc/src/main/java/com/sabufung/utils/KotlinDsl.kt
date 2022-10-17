@file:Suppress("NOTHING_TO_INLINE")

package com.sabufung.utils

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.add
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.exclude

fun DependencyHandler.dependAs(
    configuration: String,
    notation: Any,
    dependencyConfiguration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? =
    dependencyConfiguration?.let {
        if (notation is String) {
            add(configuration, notation, dependencyConfiguration)
        } else {
            add(configuration, notation)
        }
    } ?: add(configuration, notation)

fun DependencyHandler.api(
    notation: Any,
    dependencyConfiguration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? = dependAs("api", notation, dependencyConfiguration)

fun DependencyHandler.debugApi(
    notation: Any,
    dependencyConfiguration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? = dependAs("debugApi", notation, dependencyConfiguration)

fun DependencyHandler.implementation(
    notation: Any,
    dependencyConfiguration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? = dependAs("implementation", notation, dependencyConfiguration)

fun DependencyHandler.debugImplementation(
    notation: Any,
    dependencyConfiguration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? = dependAs("debugImplementation", notation, dependencyConfiguration)

fun DependencyHandler.devImplementation(
    notation: Any,
    dependencyConfiguration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? = dependAs("devImplementation", notation, dependencyConfiguration)

fun DependencyHandler.releaseImplementation(
    notation: Any,
    dependencyConfiguration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? = dependAs("releaseImplementation", notation, dependencyConfiguration)

fun DependencyHandler.testImplementation(
    notation: Any,
    dependencyConfiguration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? = dependAs("testImplementation", notation, dependencyConfiguration)

fun DependencyHandler.androidTestImplementation(
    notation: Any,
    configuration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? = dependAs("androidTestImplementation", notation, configuration)

fun DependencyHandler.androidTestUtil(
    notation: Any,
    configuration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? = dependAs("androidTestUtil", notation, configuration)

fun DependencyHandler.testRuntimeOnly(
    notation: Any,
    configuration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? = dependAs("testRuntimeOnly", notation, configuration)

fun DependencyHandler.compileOnly(
    notation: Any,
    configuration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? = dependAs("compileOnly", notation, configuration)

fun DependencyHandler.kapt(
    notation: Any,
    dependencyConfiguration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? = dependAs("kapt", notation, dependencyConfiguration)

fun DependencyHandler.kaptTest(
    notation: Any,
    dependencyConfiguration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? = dependAs("kaptTest", notation, dependencyConfiguration)

fun DependencyHandler.kaptAndroidTest(
    notation: Any,
    dependencyConfiguration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? = dependAs("kaptAndroidTest", notation, dependencyConfiguration)

fun DependencyHandler.coreLibraryDesugaring(
    notation: Any,
    dependencyConfiguration: (ExternalModuleDependency.() -> Unit)? = null
): Dependency? = dependAs("coreLibraryDesugaring", notation, dependencyConfiguration)

inline fun <T> NamedDomainObjectContainer<T>.release(noinline configure: T.() -> Unit) =
    getByName("release", configure)

inline fun <T> NamedDomainObjectContainer<T>.debug(noinline configure: T.() -> Unit) =
    getByName("debug", configure)

inline fun <T> NamedDomainObjectContainer<T>.dev(noinline configure: T.() -> Unit) =
    getByName("dev", configure)

inline fun <T> NamedDomainObjectContainer<T>.main(noinline configure: T.() -> Unit) =
    getByName("main", configure)

inline fun Project.androidLibrary(noinline configuration: LibraryExtension.() -> Unit): Unit =
    configure(configuration)

inline fun Project.androidApp(noinline configuration: BaseAppModuleExtension.() -> Unit): Unit =
    configure(configuration)

fun <T : ModuleDependency> T.excludeFirebase() =
    exclude("com.google.firebase")

fun <T : ModuleDependency> T.excludeKotlin() =
    exclude("org.jetbrains.kotlin")

fun <T : ModuleDependency> T.excludeRxJava() =
    exclude("io.reactivex.rxjava2", "rxjava")

fun <T : ModuleDependency> T.excludeSupportLib() =
    exclude("com.android.support")

fun <T : ModuleDependency> T.excludeGuava() =
    exclude("com.google.guava", "guava")

@Suppress("UNCHECKED_CAST")
fun <V : Any> Project.propertyOrDefault(key: String, defaultValue: V) =
    properties.getOrDefault(key, defaultValue) as V

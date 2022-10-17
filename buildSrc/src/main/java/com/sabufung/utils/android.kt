package com.sabufung.utils

import com.android.build.api.dsl.ApplicationExtension

@Suppress("UnstableApiUsage")
fun ApplicationExtension.ignoreCommonPackagingOptions() =
    packagingOptions {
        resources {
            excludes.add("**/attach_hotspot_windows.dll")
            excludes.add("guardit4j.fin")
            excludes.add("LICENSE.txt")
            excludes.add("META-INF/dependencies.txt")
            excludes.add("META-INF/DEPENDENCIES.txt")
            excludes.add("META-INF/DEPENDENCIES")
            excludes.add("META-INF/library_release.kotlin_module")
            excludes.add("META-INF/license.txt")
            excludes.add("META-INF/LICENSE.txt")
            excludes.add("META-INF/LICENSE")
            excludes.add("META-INF/licenses/ASM")
            excludes.add("META-INF/MANIFEST.MF")
            excludes.add("META-INF/mp_api_logging.kotlin_module")
            excludes.add("META-INF/notice.txt")
            excludes.add("META-INF/NOTICE.txt")
            excludes.add("META-INF/NOTICE")
        }
    }

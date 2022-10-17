package com.sabufung.utils

import java.io.File
import java.util.*

fun File.toSafeProperties(): SafeProperties =
    SafeProperties(this)

open class SafeProperties(
    private val origin: File
) {
    private val properties = Properties()
    init {
        require(origin.exists()) { "Invalid file: $origin" }
        properties.load(origin.reader())
    }

    @Suppress("UNCHECKED_CAST")
    operator fun get(key: Any?): String =
        properties[key]?.toString() ?: throw NullPointerException("Missing key $key in ${origin.name}")

    fun getInt(key: Any): Int =
        get(key).toInt()
}

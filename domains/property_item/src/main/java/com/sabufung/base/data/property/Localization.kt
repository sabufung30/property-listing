package com.sabufung.base.data.property


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Localization(
    @Json(name = "de")
    val de: De,
    @Json(name = "primary")
    val primary: String
)
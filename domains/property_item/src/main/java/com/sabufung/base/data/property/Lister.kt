package com.sabufung.base.data.property


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Lister(
    @Json(name = "logoUrl")
    val logoUrl: String?,
    @Json(name = "phone")
    val phone: String?
)
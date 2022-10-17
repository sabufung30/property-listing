package com.sabufung.base.data.property


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Attachment(
    @Json(name = "file")
    val `file`: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)
package com.sabufung.base.data.property


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class De(
    @Json(name = "attachments")
    val attachments: List<Attachment>,
    @Json(name = "text")
    val text: Text,
    @Json(name = "urls")
    val urls: List<Url>
)
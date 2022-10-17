package com.sabufung.base.data.property


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetPropertiesResponse(
    @Json(name = "from")
    val from: Int,
    @Json(name = "maxFrom")
    val maxFrom: Int,
    @Json(name = "results")
    val results: List<Result>,
    @Json(name = "size")
    val size: Int,
    @Json(name = "total")
    val total: Int
)
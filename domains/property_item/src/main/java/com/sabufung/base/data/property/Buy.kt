package com.sabufung.base.data.property


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Buy(
    @Json(name = "area")
    val area: String?,
    @Json(name = "interval")
    val interval: String?,
    @Json(name = "price")
    val price: Long
)
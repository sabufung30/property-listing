package com.sabufung.base.data.property


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Prices(
    @Json(name = "buy")
    val buy: Buy,
    @Json(name = "currency")
    val currency: String,
    @Json(name = "rent")
    val rent: Rent?
)
package com.sabufung.base.data.property


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Listing(
    @Json(name = "address")
    val address: Address,
    @Json(name = "categories")
    val categories: List<String>,
    @Json(name = "characteristics")
    val characteristics: Characteristics,
    @Json(name = "id")
    val id: String,
    @Json(name = "lister")
    val lister: Lister,
    @Json(name = "localization")
    val localization: Localization,
    @Json(name = "offerType")
    val offerType: String,
    @Json(name = "prices")
    val prices: Prices
)
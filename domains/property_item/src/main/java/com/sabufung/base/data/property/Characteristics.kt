package com.sabufung.base.data.property


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Characteristics(
    @Json(name = "livingSpace")
    val livingSpace: Int?,
    @Json(name = "lotSize")
    val lotSize: Int?,
    @Json(name = "numberOfRooms")
    val numberOfRooms: Double?,
    @Json(name = "totalFloorSpace")
    val totalFloorSpace: Int?
)
package com.sabufung.base.data.property


import com.sabufung.domain.model.property.PropertyAddress
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Address(
    @Json(name = "country")
    val country: String,
    @Json(name = "geoCoordinates")
    val geoCoordinates: GeoCoordinates?,
    @Json(name = "locality")
    val locality: String,
    @Json(name = "postalCode")
    val postalCode: String,
    @Json(name = "region")
    val region: String?,
    @Json(name = "street")
    val street: String?
)

fun Address.toDomain(): PropertyAddress = PropertyAddress(
    location = locality,
    street = street,
    country = country,
    region = region,
    postalCode = postalCode
)
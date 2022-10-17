package com.sabufung.base.data.property


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListerBranding(
    @Json(name = "adActive")
    val adActive: Boolean,
    @Json(name = "address")
    val address: Address?,
    @Json(name = "isPremiumBranding")
    val isPremiumBranding: Boolean,
    @Json(name = "isQualityPartner")
    val isQualityPartner: Boolean,
    @Json(name = "legalName")
    val legalName: String,
    @Json(name = "logoUrl")
    val logoUrl: String,
    @Json(name = "name")
    val name: String?,
    @Json(name = "profilePageUrlKeyword")
    val profilePageUrlKeyword: String
)
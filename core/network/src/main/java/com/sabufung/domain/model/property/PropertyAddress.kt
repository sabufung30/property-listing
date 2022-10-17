package com.sabufung.domain.model.property

data class PropertyAddress(
    val location: String,
    val country: String,
    val region: String?,
    val street: String?,
    val postalCode: String
)

fun PropertyAddress.toFullAddress() = "${street.orEmpty()} $postalCode, $location $country".trim()

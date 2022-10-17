package com.sabufung.domain.model.property

data class Property(
    val id: String,
    val title: String,
    val images: List<String>,
    val price: Double,
    val address: PropertyAddress,
    var isBookmarked: Boolean
)


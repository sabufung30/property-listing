package com.sabufung.base.data.property


import com.sabufung.base.data.db.model.PropertyEntity
import com.sabufung.domain.model.property.Property
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "id")
    val id: String,
    @Json(name = "listerBranding")
    val listerBranding: ListerBranding?,
    @Json(name = "listing")
    val listing: Listing,
    @Json(name = "listingType")
    val listingType: ListingType,
    @Json(name = "remoteViewing")
    val remoteViewing: Boolean
)

fun Result.toDomain(): Property = Property(
    id = id,
    title = listing.localization.de.text.title,
    images = listing.localization.de.attachments.map { it.url },
    price = listing.prices.buy.price.toDouble(),
    address = listing.address.toDomain(),
    isBookmarked = false
)


fun Result.toEntity(): PropertyEntity = PropertyEntity(
    id = id,
    title = listing.localization.de.text.title,
    images = listing.localization.de.attachments.map { it.url },
    price = listing.prices.buy.price.toDouble(),
    location = listing.address.locality,
    country = listing.address.country,
    region = listing.address.region,
    street = listing.address.street,
    postalCode = listing.address.postalCode,
)
package com.sabufung.base.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sabufung.domain.model.property.Property
import com.sabufung.domain.model.property.PropertyAddress

@Entity(tableName = "properties")
data class PropertyEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val images: List<String>,
    val price: Double,
    val location: String,
    val country: String,
    val region: String?,
    val street: String?,
    val postalCode: String
)

fun PropertyEntity.toDomain(): Property = Property(
    id = id,
    title = title,
    price = price,
    address = PropertyAddress(
        location, country, region, street, postalCode
    ),
    images = images,
    isBookmarked = false
)
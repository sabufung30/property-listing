package com.sabufung.base.data.repositories

import com.sabufung.domain.model.property.Property
import kotlinx.coroutines.flow.Flow

interface PropertyRepository {

    val bookmarkStream: Flow<Set<String>>

    suspend fun getListingProperty(): Flow<List<Property>>
    suspend fun toggleBookmark(id: String, value: Boolean)
}


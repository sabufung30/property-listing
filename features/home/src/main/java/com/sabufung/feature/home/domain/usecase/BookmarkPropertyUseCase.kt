package com.sabufung.feature.home.domain.usecase

import com.sabufung.base.data.repositories.PropertyRepository
import javax.inject.Inject

class BookmarkPropertyUseCase @Inject constructor(private val propertyRepository: PropertyRepository) {
    suspend operator fun invoke(id: String, value: Boolean) = propertyRepository.toggleBookmark(id, value)
}

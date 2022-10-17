package com.sabufung.feature.home.domain.usecase

import com.sabufung.domain.model.property.Property
import com.sabufung.base.data.repositories.PropertyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPropertiesUseCase @Inject constructor(private val propertyRepository: PropertyRepository) {
    suspend operator fun invoke(): Flow<List<Property>> {
        return combine(
            propertyRepository.getListingProperty(),
            propertyRepository.bookmarkStream,
            ::Pair
        ).map { newsToBookmarksResult ->
            val (news, bookmarks) = newsToBookmarksResult
            news.map {
                it.isBookmarked = bookmarks.contains(it.id)
                it
            }
            news
        }
    }
}

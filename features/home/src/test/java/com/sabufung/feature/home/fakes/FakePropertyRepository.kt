package com.sabufung.feature.home.fakes

import com.sabufung.domain.model.property.Property
import com.sabufung.base.data.repositories.PropertyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update

class FakePropertyRepository(
    private val fakePropertyResults: List<Property>,
    private val fakeBookmarkResults: Set<String>,
    val fakePropertyService: FakePropertyService = FakePropertyService(),
) : PropertyRepository {

    val bookmarkedIds = fakeBookmarkResults.toMutableSet()
    val _bookmarkStream = MutableStateFlow(bookmarkedIds)
    override val bookmarkStream: Flow<Set<String>> = _bookmarkStream

    override suspend fun getListingProperty(): Flow<List<Property>> {
        return flow {
            emit(fakePropertyResults)
        }
    }

    override suspend fun toggleBookmark(id: String, value: Boolean) {
        if (value) {
            bookmarkedIds.add(id)
        } else {
            bookmarkedIds.remove(id)
        }
        _bookmarkStream.update { bookmarkedIds }
    }


}
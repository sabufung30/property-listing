package com.sabufung.feature.home

import com.sabufung.feature.home.domain.usecase.BookmarkPropertyUseCase
import com.sabufung.feature.home.fakes.FakePropertyRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class BookmarkPropertyUseCaseTest {

    private val samplePropertyData = PropertyTestData.property()

    private val sampleBookmarkedIds = setOf(
        "104123262"
    )
    private val fakePropertyRepository = FakePropertyRepository(samplePropertyData, sampleBookmarkedIds)
    private val bookmarkPropertyUseCase = BookmarkPropertyUseCase(fakePropertyRepository)

    @Test
    fun `bookmarkPropertyUseCase should save one property as bookmarked`() = runBlockingTest {
        val propertyIdToBookmark = "3001737958"
        assertEquals(sampleBookmarkedIds.size, fakePropertyRepository.bookmarkStream.firstOrNull()?.size)
        bookmarkPropertyUseCase(propertyIdToBookmark, true)
        assertEquals(sampleBookmarkedIds.size + 1, fakePropertyRepository.bookmarkStream.firstOrNull()?.size)
        assertTrue(fakePropertyRepository.bookmarkStream.firstOrNull()?.contains(propertyIdToBookmark) ?: false)
    }

    @Test
    fun `bookmarkPropertyUseCase should remove one property as not bookmarked`() = runBlockingTest {
        val propertyIdToBookmark = "104123262"
        assertEquals(sampleBookmarkedIds.size, fakePropertyRepository.bookmarkStream.firstOrNull()?.size)
        bookmarkPropertyUseCase(propertyIdToBookmark, false)
        assertEquals(sampleBookmarkedIds.size - 1, fakePropertyRepository.bookmarkStream.firstOrNull()?.size)
        assertFalse(fakePropertyRepository.bookmarkStream.firstOrNull()?.contains(propertyIdToBookmark) ?: true)
    }

}

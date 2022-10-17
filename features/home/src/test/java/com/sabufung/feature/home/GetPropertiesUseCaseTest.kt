package com.sabufung.feature.home

import com.sabufung.feature.home.domain.usecase.GetPropertiesUseCase
import com.sabufung.feature.home.fakes.FakePropertyRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPropertiesUseCaseTest {

    private val sampleBookmarkedIds = setOf(
        "104123262", "3001738027"
    )

    private val samplePropertyListing = PropertyTestData.property()

    private val fakePropertyRepository = FakePropertyRepository(samplePropertyListing, sampleBookmarkedIds)

    private val getPropertiesUseCase = GetPropertiesUseCase(fakePropertyRepository)

    @Test
    fun `getPropertiesUseCase should return list of property`() = runBlockingTest {
        assertEquals(samplePropertyListing.size, getPropertiesUseCase().firstOrNull()?.size)
    }

    @Test
    fun `getPropertiesUseCase should return list of property with bookmark`() = runBlockingTest {
        assertEquals(sampleBookmarkedIds.size, getPropertiesUseCase().firstOrNull()?.filter { it.isBookmarked }?.size)
        assertEquals(true, getPropertiesUseCase().firstOrNull()?.map { it.id }?.contains(sampleBookmarkedIds.first()))
    }

}

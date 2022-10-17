
package com.sabufung.feature.home

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import com.sabufung.data.LoadingState
import com.sabufung.feature.home.fakes.FakeListingViewModel
import com.sabufung.feature.home.presentation.ListingPageScreen
import com.sabufung.feature.home.presentation.ListingPageScreenTestTag.EMPTY_DATA_TEXT_TAG
import com.sabufung.feature.home.presentation.ListingPageScreenTestTag.PROPERTY_LIST_TAG
import com.sabufung.feature.home.presentation.ListingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@OptIn(ExperimentalCoroutinesApi::class)
@Config(instrumentedPackages = ["androidx.loader.content"])
@RunWith(RobolectricTestRunner::class)
class ListingPageScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun beforeEach() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun afterEach() {
        testDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun `ListingPageScreen should show empty data text when no data`() {
        composeTestRule.setContent {
            ListingPageScreen(viewModel = FakeListingViewModel(
                ListingState(
                    properties = LoadingState(
                        data = listOf()
                    )
                )
            ))
        }

        composeTestRule.onNodeWithTag(EMPTY_DATA_TEXT_TAG).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `ListingPageScreen should show list of property text when there is properties data`() = runBlockingTest {
        val testData = PropertyTestData.property()
        composeTestRule.setContent {
            ListingPageScreen(viewModel = FakeListingViewModel(
                ListingState(
                    properties = LoadingState(
                        data = testData
                    )
                )
            ))
        }
        composeTestRule.apply {
            onNodeWithTag(PROPERTY_LIST_TAG).onChildren().onFirst().assert(hasText(testData.first().title))
            onNodeWithTag(PROPERTY_LIST_TAG).onChildren()[1].assert(hasText(testData[1].title))

            // Scrolling to bottom since LazyColumn do not populate full data
            onNodeWithTag(PROPERTY_LIST_TAG).performScrollToIndex(testData.lastIndex)
            onNodeWithTag(PROPERTY_LIST_TAG).onChildren().assertCountEquals(testData.size)
        }
    }
    
}
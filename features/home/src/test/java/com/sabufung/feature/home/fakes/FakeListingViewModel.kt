package com.sabufung.feature.home.fakes

import com.sabufung.feature.home.presentation.ListingEvent
import com.sabufung.feature.home.presentation.ListingNavigationAction
import com.sabufung.feature.home.presentation.ListingState
import com.sabufung.feature.home.presentation.ListingViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

class FakeListingViewModel(state: ListingState) : ListingViewModel {
    override val state: StateFlow<ListingState> = MutableStateFlow(state)
    override val navigationAction: Flow<ListingNavigationAction> = flow {}

    override fun onEvent(event: ListingEvent) {

    }
}

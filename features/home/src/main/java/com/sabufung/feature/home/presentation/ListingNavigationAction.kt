package com.sabufung.feature.home.presentation

sealed class ListingNavigationAction {
    class NavigateToPropertyDetail(
        val propertyId: String
    ) : ListingNavigationAction()
}

package com.sabufung.feature.home.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sabufung.feature.home.presentation.ListingDestinations.LISTING
import com.sabufung.navigation.navigateOnEach

fun NavGraphBuilder.listingNavigation(navController: NavController) {

    navigation(
        startDestination = LISTING,
        route = ListingDestinations.ROUTE
    ) {
        val navigator = ListingNavigator(navController)

        composable(
            route = LISTING,
        ) {
            val listingViewModel: ListingViewModel = hiltViewModel<ListingViewModelImpl>()

            listingViewModel.navigationAction.navigateOnEach {
                when (it) {
                    is ListingNavigationAction.NavigateToPropertyDetail -> navigator.onPropertyClick(it.propertyId)
                }
            }
            ListingPageScreen(
                listingViewModel
            )
        }
    }
}

class ListingNavigator(
    navController: NavController,
) {
    fun onPropertyClick(propertyId: String) {
        TODO("Not yet implemented")
    }
}

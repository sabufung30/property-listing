package com.sabufung.app.mad.bottombar

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navigation
import com.sabufung.feature.home.presentation.ListingDestinations
import com.sabufung.feature.home.presentation.listingNavigation
import com.sabufung.feature.profile.profileNavigation
import com.sabufung.navigation.BottomDestinations

fun NavGraphBuilder.bottomNavigation(
    navController: NavController
) {

    navigation(
        route = BottomDestinations.ROUTE,
        startDestination = ListingDestinations.ROUTE,
    ) {
        listingNavigation(navController)

        profileNavigation(navController)
    }
}

class BottomNavigator(
    private val navController: NavController
) {

    private var _currentBottomDestination = mutableStateOf<String?>(ListingDestinations.LISTING)
    val currentBottomDestination: State<String?> = _currentBottomDestination

    init {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // listen to all explicit navigation to the bottom destination.
            val currentRoute = destination.route
            if (currentRoute in BottomDestinations.destinations) {
                _currentBottomDestination.value = currentRoute
            }
        }
    }

    val toHome: () -> Unit = {
        navController.popBackStack(
            route = ListingDestinations.LISTING,
            inclusive = false,
        )
    }

    val toProfile: () -> Unit = {
        navController.navigateTo(BottomDestinations.PROFILE) {
            popUpToHome()
        }
    }

    private fun NavController.navigateTo(
        route: String,
        builder: NavOptionsBuilder.() -> Unit = {}
    ) {
        val currentRoute = currentBackStackEntry?.destination?.route
        if (currentRoute != route) {
            navigate(route, builder)
        }
    }

    private fun NavOptionsBuilder.popUpToHome(inclusive: Boolean = false) {
        popUpTo(ListingDestinations.LISTING) { this.inclusive = inclusive }
        launchSingleTop = true
    }
}

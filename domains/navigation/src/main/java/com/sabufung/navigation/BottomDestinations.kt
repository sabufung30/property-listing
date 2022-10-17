package com.sabufung.navigation

/**
 * Special set of Global Navigation that is paired with the Bottom navigation bar
 */
object BottomDestinations {
    const val ROUTE = "bottom_nav"

    const val HOME = "listing"
    const val PROFILE = "profile"

    val destinations = setOf(
        HOME,
        PROFILE
    )
}

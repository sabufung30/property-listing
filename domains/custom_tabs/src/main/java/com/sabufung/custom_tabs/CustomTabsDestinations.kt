package com.sabufung.custom_tabs

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.sabufung.custom_tabs.CustomTabsDestinations.ARG_URL
import com.sabufung.custom_tabs.CustomTabsDestinations.CUSTOM_TABS

fun NavGraphBuilder.customTabsNavigation() {
    customTabs(
        route = "$CUSTOM_TABS/{$ARG_URL}",
        arguments = listOf(
            navArgument(ARG_URL) {}
        ),
        customTabsIntentBuilder = {
            // TODO styling
        },
        urlToLaunch = { args ->
            Uri.parse(
                requireNotNull(args?.getString(ARG_URL)) {
                    "$ARG_URL argument is required but is not found."
                }
            )
        }
    )
}

object CustomTabsDestinations {
    const val CUSTOM_TABS = "customTabs"
    const val ARG_URL = "url"
}

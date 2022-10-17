package com.sabufung.custom_tabs

import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.get

fun NavGraphBuilder.customTabs(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    customTabsIntentBuilder: CustomTabsIntent.Builder.() -> Unit,
    urlToLaunch: (args: Bundle?) -> Uri,
) {
    addDestination(
        CustomTabsNavigator.Destination(
            provider[CustomTabsNavigator::class],
            buildCustomTabs(customTabsIntentBuilder),
            urlToLaunch
        ).apply {
            this.route = route
            arguments.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}

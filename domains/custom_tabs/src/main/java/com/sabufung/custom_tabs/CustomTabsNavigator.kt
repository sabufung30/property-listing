package com.sabufung.custom_tabs

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

@Navigator.Name("CustomTabsNavigator")
class CustomTabsNavigator(
    private val context: Context,
) : Navigator<CustomTabsNavigator.Destination>() {

    class Destination(
        navigator: CustomTabsNavigator,
        internal val customTabsIntent: CustomTabsIntent,
        internal val urlToLaunch: (Bundle?) -> Uri,
    ) : NavDestination(navigator)

    override fun createDestination(): Destination = Destination(
        navigator = this,
        customTabsIntent = buildCustomTabs { }
    ) { Uri.EMPTY }

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Extras?
    ): NavDestination? {

        destination.run {
            // TODO warmup the browser for optimized performance
            customTabsIntent.launchUrl(
                context,
                urlToLaunch(args),
            )
        }

        // don't add to backstack as it is launched in a new activity
        return null
    }
}

@Composable
fun rememberCustomTabsNavigator(): CustomTabsNavigator {
    val context = LocalContext.current
    return remember {
        CustomTabsNavigator(context)
    }
}

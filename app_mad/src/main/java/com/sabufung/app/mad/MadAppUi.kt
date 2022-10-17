package com.sabufung.app.mad

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.plusAssign
import com.sabufung.app.ds.component.SnackbarHost
import com.sabufung.app.ds.component.SnackbarHostState
import com.sabufung.app.ds.theme.DsSizing
import com.sabufung.app.mad.bottombar.BottomNavigationBar
import com.sabufung.app.mad.bottombar.BottomNavigator
import com.sabufung.app.mad.bottombar.bottomNavigation
import com.sabufung.app.ui.LocalSnackbarHostState
import com.sabufung.custom_tabs.rememberCustomTabsNavigator
import com.sabufung.navigation.BottomDestinations
import com.sabufung.navigation.MainDestinations
import com.sabufung.navigation.NavDispatcher
import kotlinx.coroutines.flow.collect

@Composable
fun MadAppUi(
    navDispatcher: NavDispatcher
) {
    val navController = rememberNavController()
    LaunchedEffect(navController) {
        navDispatcher.navigationCommands.collect { command ->
            command.invoke(
                navController
            )
        }
    }

    val bottomNavigator = remember { BottomNavigator(navController) }
    val snackbarHostState = remember { SnackbarHostState() }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    CompositionLocalProvider(
        LocalSnackbarHostState provides snackbarHostState,
    ) {
        val isBottomBarShown = BottomDestinations.destinations.contains(currentBackStackEntry?.destination?.route)
        Scaffold(
            bottomBar = {
                if (isBottomBarShown) {
                    BottomNavigationBar(
                        navigator = bottomNavigator,
                    )
                }
            },
            snackbarHost = {
                SnackbarHost(
                    state = snackbarHostState,
                    modifier = Modifier.padding(DsSizing.spacing16)
                )
            }
        ) { paddingValues ->

            Box(Modifier.padding(paddingValues)) {

                val customTabsNavigator = rememberCustomTabsNavigator()
                navController.navigatorProvider += customTabsNavigator
                NavHost(
                    navController = navController,
                    startDestination = BottomDestinations.ROUTE,
                    route = MainDestinations.ROUTE,
                ) {

                    bottomNavigation(navController)

                }

            }
        }
    }
}

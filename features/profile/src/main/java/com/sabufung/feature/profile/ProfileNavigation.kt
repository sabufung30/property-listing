package com.sabufung.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sabufung.app.ds.theme.DsColors
import com.sabufung.navigation.BottomDestinations

fun NavGraphBuilder.profileNavigation(navController: NavController) {
    navigation(
        route = ProfileDestinations.ROUTE,
        startDestination = ProfileDestinations.PROFILE,
    ) {

        composable(
            route = ProfileDestinations.PROFILE
        ) {
            Scaffold(
                topBar = {

                },
            ) { rootPadding ->
                Column(
                    modifier = Modifier
                        .padding(rootPadding)
                        .fillMaxSize()
                        .background(DsColors.backgroundPrimary)
                ) {
                    Text(text = "Profile")
                }
            }
        }
    }
}


object ProfileDestinations {
    const val ROUTE = "profile_nav"
    const val PROFILE = BottomDestinations.PROFILE
}

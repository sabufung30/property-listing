package com.sabufung.app.mad.bottombar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sabufung.app.ds.component.BottomNavigation
import com.sabufung.app.ds.component.BottomNavigationItem
import com.sabufung.app.ds.component.Separator
import com.sabufung.app.ds.theme.DsIcons
import com.sabufung.app.mad.R
import com.sabufung.feature.home.presentation.ListingDestinations
import com.sabufung.navigation.BottomDestinations

@Composable
fun BottomNavigationBar(
    navigator: BottomNavigator,
) {
    Column {
        Separator()

        BottomNavigation {
            val currentBottomDestination = navigator.currentBottomDestination.value

            HomeItem(
                currentBottomDestination,
                navigator.toHome,
            )

            ProfileItem(
                currentBottomDestination,
                navigator.toProfile,
            )
        }
    }
}

@Composable
private fun RowScope.HomeItem(
    currentBottomDestination: String?,
    onClick: () -> Unit,
) {
    BottomNavigationItem(
        label = stringResource(R.string.bottom_nav_title_home),
        icon = DsIcons.homeOutlined,
        iconSelected = DsIcons.homeFilled,
        isSelected = currentBottomDestination == ListingDestinations.LISTING,
        onClick = onClick
    )
}


@Composable
private fun RowScope.ProfileItem(
    currentBottomDestination: String?,
    onClick: () -> Unit,
) {
    BottomNavigationItem(
        label = stringResource(R.string.bottom_nav_title_debug),
        icon = DsIcons.personOutlined,
        iconSelected = DsIcons.personFilled,
        isSelected = currentBottomDestination == BottomDestinations.PROFILE,
        onClick = onClick
    )
}

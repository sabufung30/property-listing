package com.sabufung.app.ds.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sabufung.app.ds.component.button.FlatButton
import com.sabufung.app.ds.component.button.IconButton
import com.sabufung.app.ds.theme.DsColors
import com.sabufung.app.ds.theme.DsSizing
import com.sabufung.app.ds.theme.DsTypography

/**
 * @sample com.sabufung.app.ds.catalog.component.NavigationActivity
 *
 * @param title string for the title
 * @param navigationIcon the vector asset of Icon displayed at the start of the TopNavigation
 * @param onNavigationClick the lambda to be invoked when the navigation icon is pressed
 * @param actionLabel the label of the [FlatButton] displayed at the end of the TopNavigation
 * @param onActionClick the lambda to be invoked when the [FlatButton] at the end is pressed
 */
@Composable
fun TopNavigation(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: Painter? = null,
    onNavigationClick: () -> Unit = {},
    actionLabel: String,
    onActionClick: () -> Unit = {}
) {
    TopNavigation(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        onNavigationClick = onNavigationClick,
        actions = {
            FlatButton(text = actionLabel, onClick = onActionClick)
        }
    )
}

/**
 * @sample com.sabufung.app.ds.catalog.component.NavigationActivity
 *
 * @param title string for the title
 * @param navigationIcon the vector asset of Icon displayed at the start of the TopNavigation
 * @param onNavigationClick the lambda to be invoked when the navigation icon is pressed
 * @param actions [TopNavigationItem]s displayed at the end of the TopNavigation
 */
@Composable
fun TopNavigation(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: Painter? = null,
    navigationIconContentDescription: String? = null,
    onNavigationClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopNavigation(
        modifier = modifier,
        navigationIcon = navigationIcon,
        navigationIconContentDescription = navigationIconContentDescription,
        onNavigationClick = onNavigationClick,
        actions = actions,
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(horizontal = DsSizing.spacing4),
            style = DsTypography.largeStrong,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

/**
 * @sample com.sabufung.app.ds.catalog.component.NavigationActivity
 *
 * TopNavigation is a wrapper of [TopAppBar]
 *
 * @param navigationIcon the vector asset of Icon displayed at the start of the TopNavigation
 * @param onNavigationClick the lambda to be invoked when the navigation icon is pressed
 * @param actions [TopNavigationItem]s displayed at the end of the TopNavigation
 * @param content components displayed in the center of the TopNavigation
 */
@Composable
fun TopNavigation(
    modifier: Modifier = Modifier,
    navigationIcon: Painter? = null,
    navigationIconContentDescription: String? = null,
    onNavigationClick: () -> Unit = {},
    showBottomSeparator: Boolean = true,
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable RowScope.() -> Unit
) {
    Box(
        modifier = modifier
    ) {
        TopAppBar(
            backgroundColor = DsColors.backgroundPrimary,
            contentColor = DsColors.textPrimary,
            elevation = 0.dp
        ) {
            if (navigationIcon == null) {
                HorizontalSpacer(DsSizing.spacing4)
            } else {
                Row(
                    Modifier
                        .fillMaxHeight()
                        .padding(end = DsSizing.spacing4),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TopNavigationItem(
                        iconPainter = navigationIcon,
                        contentDescription = navigationIconContentDescription,
                        onClick = onNavigationClick
                    )
                }
            }

            Row(
                Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                content()
            }

            Row(
                Modifier
                    .fillMaxHeight()
                    .padding(start = DsSizing.spacing4),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                content = actions
            )
        }

        if (LocalTopNavigationBottomSeparatorEnabled.current && showBottomSeparator) {
            Separator(
                modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

/**
 * @sample com.sabufung.app.ds.catalog.component.NavigationActivity
 *
 * TopNavigationItem is a [Box] contains an [IconButton] and a [Badge]
 *
 * @param iconPainter vector asset for the [IconButton]
 * @param onClick the lambda to be invoked when this icon is pressed
 * @param badge showing a [Badge] or not by keeping it empty
 */
@Composable
fun TopNavigationItem(
    iconPainter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    badge: @Composable () -> Unit = {},
    onClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopEnd
    ) {
        androidx.compose.material.IconButton(
            onClick = onClick,
            modifier = modifier.size(DsSizing.measure40),
            enabled = enabled
        ) {
            Icon(
                painter = iconPainter,
                contentDescription = contentDescription,
                modifier = Modifier.size(DsSizing.measure24),
                tint = if (enabled) DsColors.iconPrimary else DsColors.iconDisabled
            )
        }

        badge()
    }
}

private val LocalTopNavigationBottomSeparatorEnabled = compositionLocalOf { true }

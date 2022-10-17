package com.sabufung.app.ds.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.sabufung.app.ds.theme.DsColors
import com.sabufung.app.ds.theme.DsSizing
import com.sabufung.app.ds.theme.DsTypography

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    BaseBottomNavigation(
        modifier = modifier,
        backgroundColor = DsColors.backgroundPrimary,
        contentColor = DsColors.textPrimary
    ) {
        content()
    }
}

@Composable
private fun BaseBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = BottomNavigationDefaults.Elevation,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        modifier = modifier
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(DsSizing.spacing8),
            content = content
        )
    }
}

@Composable
fun RowScope.BottomNavigationItem(
    icon: Painter,
    label: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    iconSelected: Painter? = null,
    selectedLabelColor: Color = DsColors.textPrimary,
    unselectedLabelColor: Color = DsColors.textPrimary,
    contentDescription: String? = null,
    badge: @Composable () -> Unit = {},
    onClick: () -> Unit
) {
    BottomNavigationItem(
        icon = {
            val optedIcon = if (isSelected && iconSelected != null) iconSelected else icon
            BottomNavigationIcon(
                painter = optedIcon,
                contentDescription = contentDescription,
                badge = badge
            )
        },
        modifier = modifier,
        label = label,
        selected = isSelected,
        selectedLabelColor = selectedLabelColor,
        unselectedLabelColor = unselectedLabelColor,
        onClick = onClick
    )
}

/**
 * Use this variant when you don't want to show labels. Content Description here is a must
 * because we need to consider talkback a11y description of the icon due to lack of label.
 */
@Composable
fun RowScope.BottomNavigationItem(
    icon: Painter,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    iconSelected: Painter? = null,
    selectedLabelColor: Color = DsColors.textPrimary,
    unselectedLabelColor: Color = DsColors.textPrimary,
    contentDescription: String,
    badge: @Composable () -> Unit = {},
    onClick: () -> Unit
) {
    BottomNavigationItem(
        icon = {
            val optedIcon = if (isSelected && iconSelected != null) iconSelected else icon
            BottomNavigationIcon(
                painter = optedIcon,
                contentDescription = contentDescription,
                badge = badge
            )
        },
        modifier = modifier,
        selected = isSelected,
        selectedLabelColor = selectedLabelColor,
        unselectedLabelColor = unselectedLabelColor,
        onClick = onClick
    )
}

@Composable
private fun RowScope.BottomNavigationItem(
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    selected: Boolean = false,
    selectedLabelColor: Color,
    unselectedLabelColor: Color,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .selectable(
                selected = selected,
                onClick = {
                    onClick()
                },
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false)
            )
            .wrapContentHeight()
            .weight(1f)
            .padding(vertical = DsSizing.spacing8),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        icon()

        label?.run {
            val defaultTextStyle = DsTypography.xSmall
            val adjustedTextStyle = DsTypography.x3Small
            val textStyle = remember { mutableStateOf(defaultTextStyle) }

            Text(
                text = this,
                color = if (selected) selectedLabelColor else unselectedLabelColor,
                style = textStyle.value,
                textAlign = TextAlign.Center,
                onTextLayout = { textLayoutResult ->
                    if (textLayoutResult.lineCount > 1 && textStyle.value == defaultTextStyle) {
                        textStyle.value = adjustedTextStyle
                    }
                }
            )
        }

    }
}

@Composable
private fun BottomNavigationIcon(
    painter: Painter,
    contentDescription: String?,
    badge: @Composable () -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier.wrapContentSize()
    ) {
        val (icon, countBadge) = createRefs()

        Icon(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(DsSizing.measure24)
        )

        Box(
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(countBadge) {
                    top.linkTo(icon.top)
                    end.linkTo(icon.end)
                }
                // Slight adjustment of the badge
                .offset(
                    x = 7.dp,
                    y = (-2).dp
                )
        ) {
            badge()
        }
    }
}

internal object BottomNavigationDefaults {
    /**
     * Default elevation used for [BottomNavigation].
     */
    val Elevation = 8.dp
}

package com.sabufung.app.ds.component.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sabufung.app.ds.theme.DsColors
import com.sabufung.app.ds.theme.DsRadius
import com.sabufung.app.ds.theme.DsSizing
import com.sabufung.app.ds.theme.DsTypography

object IconButton {
    sealed class Variant {
        @Composable
        abstract fun backgroundColor(enabled: Boolean): Color

        @Composable
        abstract fun iconColor(enabled: Boolean): Color

        // opacity used for disable state
        open fun backgroundOpacity(enabled: Boolean): Float = 1f
        open fun iconOpacity(enabled: Boolean): Float = 1f

        object Primary : Variant() {
            @Composable
            override fun backgroundColor(enabled: Boolean) = DsColors.backgroundPrimary

            @Composable
            override fun iconColor(enabled: Boolean) =
                if (enabled) DsColors.iconPrimary else DsColors.iconDisabled
        }

        object Secondary : Variant() {
            @Composable
            override fun backgroundColor(enabled: Boolean) = DsColors.overlayMiddle

            @Composable
            override fun iconColor(enabled: Boolean) = DsColors.iconInverse

            override fun backgroundOpacity(enabled: Boolean) =
                if (enabled) 1f else 0.4f

            override fun iconOpacity(enabled: Boolean) =
                if (enabled) 1f else 0.4f
        }

        object Tertiary : Variant() {
            @Composable
            override fun backgroundColor(enabled: Boolean) = Color.Transparent

            @Composable
            override fun iconColor(enabled: Boolean) =
                if (enabled) DsColors.iconPrimary else DsColors.iconDisabled
        }

        object Static : Variant() {
            @Composable
            override fun backgroundColor(enabled: Boolean) =
                DsColors.staticBlack.copy(alpha = 0.2f)

            @Composable
            override fun iconColor(enabled: Boolean) = DsColors.staticWhite

            override fun iconOpacity(enabled: Boolean) =
                if (enabled) 1f else 0.4f
        }
    }
}

/**
 * @sample com.sabufung.app.ds.catalog.component.button.IconButtonScreen
 *
 * @param iconVector [ImageVector] to draw inside this Icon
 * @param variant [IconButton.Variant] IconButton variants
 * one from [.Primary, .Secondary, .Tertiary, .Static], default is [.Primary]
 * @param enabled whether or not this IconButton will handle input events and appear enabled for
 * semantics purposes
 * @param contentDescription - text used by accessibility services to describe what this image
 * @param onClick the lambda to be invoked when this IconButton is pressed
 */
@Composable
fun IconButton(
    iconVector: Painter,
    modifier: Modifier = Modifier,
    variant: IconButton.Variant = IconButton.Variant.Primary,
    enabled: Boolean = LocalButtonEnabled.current,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    androidx.compose.material.IconButton(
        onClick = {
            onClick()
        },
        modifier = modifier
            .size(DsSizing.measure48),
        enabled = enabled
    ) {
        Surface(
            modifier = Modifier.alpha(variant.backgroundOpacity(enabled)),
            shape = RectangleShape,
            color = variant.backgroundColor(enabled),
            contentColor = variant.iconColor(enabled)
        ) {
            Icon(
                modifier = Modifier
                    .padding(DsSizing.spacing8)
                    .size(DsSizing.measure36)
                    .alpha(variant.iconOpacity(enabled)),
                painter = iconVector,
                contentDescription = contentDescription,
            )
        }
    }
}


/**
 * @sample com.sabufung.app.ds.catalog.component.button.IconButtonScreen
 *
 * @param iconVector [ImageVector] to draw inside this Icon
 * @param variant [IconButton.Variant] IconButton variants
 * one from [.Primary, .Secondary, .Tertiary, .Static], default is [.Primary]
 * @param enabled whether or not this IconButton will handle input events and appear enabled for
 * semantics purposes
 * @param contentDescription - text used by accessibility services to describe what this image
 * @param onClick the lambda to be invoked when this IconButton is pressed
 */
@Composable
fun IconButton(
    iconVector: Painter,
    modifier: Modifier = Modifier,
    variant: IconButton.Variant = IconButton.Variant.Primary,
    enabled: Boolean = LocalButtonEnabled.current,
    contentDescription: String? = null,
    tint: Color,
    onClick: () -> Unit
) {
    androidx.compose.material.IconButton(
        onClick = {
            onClick()
        },
        modifier = modifier
            .size(DsSizing.measure48),
        enabled = enabled
    ) {
        Surface(
            modifier = Modifier.alpha(variant.backgroundOpacity(enabled)),
            shape = RectangleShape,
            color = variant.backgroundColor(enabled),
            contentColor = tint
        ) {
            Icon(
                modifier = Modifier
                    .padding(DsSizing.spacing8)
                    .size(DsSizing.measure36)
                    .alpha(variant.iconOpacity(enabled)),
                painter = iconVector,
                contentDescription = contentDescription,
            )
        }
    }
}

/**
 * @sample com.sabufung.app.ds.catalog.component.button.IconButtonScreen
 *
 * @param iconVector [ImageVector] to draw inside this Icon
 * @param label label to show a text under the Icon
 * @param enabled whether or not this IconButton will handle input events and appear enabled for
 * semantics purposes
 * @param contentDescription - text used by accessibility services to describe what this image
 * @param onClick the lambda to be invoked when this IconButton is pressed
 */
@Composable
fun IconButton(
    iconVector: Painter,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = LocalButtonEnabled.current,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    val clickable = if (enabled) Modifier.clickable {
        onClick()
    } else Modifier
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(DsRadius.medium)) // shape for the ripple
            .then(clickable),
        shape = RoundedCornerShape(DsRadius.medium),
        color = DsColors.backgroundPrimary
    ) {
        Column(
            modifier = Modifier
                .padding(DsSizing.spacing4)
                .widthIn(min = BUTTON_MIN_WIDTH.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = iconVector,
                contentDescription = contentDescription,
                modifier = Modifier.size(DsSizing.measure24),
                tint = if (enabled) DsColors.iconPrimary else DsColors.iconDisabled
            )

            Text(
                text = label,
                color = if (enabled) DsColors.textPrimary else DsColors.textDisabled,
                overflow = TextOverflow.Ellipsis,
                maxLines = LABEL_MAX_LINES,
                style = DsTypography.xSmall
            )
        }
    }
}

private const val BUTTON_MIN_WIDTH = 56
private const val LABEL_MAX_LINES = 2

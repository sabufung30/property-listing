package com.sabufung.app.ds.component

import androidx.annotation.IdRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sabufung.app.ds.component.button.Button
import com.sabufung.app.ds.component.button.CustomButton
import com.sabufung.app.ds.component.button.FlatButton
import com.sabufung.app.ds.theme.DsBorders
import com.sabufung.app.ds.theme.DsColors
import com.sabufung.app.ds.theme.DsRadius
import com.sabufung.app.ds.theme.DsShadows
import com.sabufung.app.ds.theme.DsSizing
import com.sabufung.app.ds.theme.DsTypography
import androidx.compose.material.SnackbarHostState as MaterialSnackbarHostState

object Snackbar {
    sealed class Variant {
        @get:Composable
        abstract val backgroundColor: Color

        @get:Composable
        abstract val iconTint: Color

        @get:Composable
        abstract val borderColor: Color

        @get:Composable
        abstract val contentColor: Color

        object Default : Variant() {
            override val backgroundColor: Color
                @Composable
                get() = DsColors.backgroundAccentThin

            override val iconTint: Color
                @Composable
                get() = DsColors.iconAccent

            override val borderColor: Color
                @Composable
                get() = DsColors.borderAccent

            override val contentColor: Color
                @Composable
                get() = DsColors.backgroundAccentThinHighlight
        }

        object Attention : Variant() {
            override val backgroundColor: Color
                @Composable
                get() = DsColors.backgroundAttentionThin

            override val iconTint: Color
                @Composable
                get() = DsColors.iconAttention

            override val borderColor: Color
                @Composable
                get() = DsColors.borderAttention

            override val contentColor: Color
                @Composable
                get() = DsColors.backgroundAttentionThinHighlight
        }
    }
}

@Composable
fun Snackbar(
    text: String,
    modifier: Modifier = Modifier,
    variant: Snackbar.Variant = Snackbar.Variant.Default,
    caption: String? = null,
    icon: Painter? = null,
    iconContentDescription: String? = null
) {
    Snackbar(
        text = text,
        modifier = modifier,
        caption = caption,
        icon = icon,
        iconContentDescription = iconContentDescription,
        variant = variant,
        action = null,
    )
}

@Composable
fun Snackbar(
    text: String,
    modifier: Modifier = Modifier,
    variant: Snackbar.Variant = Snackbar.Variant.Default,
    caption: String? = null,
    icon: Painter? = null,
    iconContentDescription: String? = null,
    actionLabel: String,
    onActionClick: () -> Unit
) {
    Snackbar(
        text = text,
        modifier = modifier,
        caption = caption,
        icon = icon,
        iconContentDescription = iconContentDescription,
        variant = variant,
        action = {
            FlatButton(
                text = actionLabel,
                sizeVariant = Button.SizeVariant.Small,
                onClick = onActionClick
            )
        }
    )
}

/**
 * For SnackbarHost
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Snackbar(
    data: SnackbarData,
    modifier: Modifier = Modifier,
    variant: Snackbar.Variant = Snackbar.Variant.Default,
    caption: String? = null,
    icon: Painter? = null,
    iconContentDescription: String? = null
) {
    val actionLabel = data.actionLabel
    val action: (@Composable () -> Unit)? = if (actionLabel != null) {
        {
            CustomButton(
                label = CustomButton.Label(
                    text = actionLabel,
                    color = DsColors.textAttentionActive
                ),
                theme = CustomButton.Theme(
                    backgroundColor = variant.backgroundColor,
                    disabledBackgroundColor = variant.backgroundColor,
                    borderColor = DsColors.staticClear,
                    disabledBorderColor = DsColors.staticClear,
                    contentColor = variant.contentColor,
                    disabledContentColor = variant.contentColor
                ),
                sizeVariant = Button.SizeVariant.Small,
                onClick = {
                    data.performAction()
                }
            )
        }
    } else {
        null
    }
    Snackbar(
        text = data.message,
        modifier = modifier,
        caption = caption,
        variant = variant,
        icon = icon,
        iconContentDescription = iconContentDescription,
        action = action
    )
}

@Composable
private fun Snackbar(
    text: String,
    modifier: Modifier = Modifier,
    variant: Snackbar.Variant,
    caption: String? = null,
    icon: Painter? = null,
    iconContentDescription: String?,
    action: @Composable (() -> Unit)?
) {
    Surface(
        modifier = modifier.then(Modifier.fillMaxWidth()),
        shape = RoundedCornerShape(DsRadius.medium),
        color = variant.backgroundColor,
        border = BorderStroke(DsBorders.normal, variant.borderColor),
        elevation = DsShadows.middleFloor
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = DsSizing.spacing16,
                vertical = DsSizing.spacing8
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Icon(
                    painter = it,
                    contentDescription = iconContentDescription,
                    modifier = Modifier
                        .size(DsSizing.measure24)
                        .padding(end = DsSizing.spacing4),
                    tint = variant.iconTint
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = text,
                    color = DsColors.textPrimary,
                    maxLines = TEXT_MAX_LINE,
                    overflow = TextOverflow.Ellipsis,
                    style = DsTypography.medium
                )

                caption?.let {
                    Text(
                        text = caption,
                        modifier = Modifier.padding(top = DsSizing.spacing4),
                        color = DsColors.textPrimary,
                        maxLines = CAPTION_MAX_LINE,
                        overflow = TextOverflow.Ellipsis,
                        style = DsTypography.x2Small
                    )
                }
            }

            action?.let {
                Box(
                    modifier = Modifier
                        .widthIn(max = ACTION_MAX_WIDTH.dp)
                        .padding(start = DsSizing.spacing4)
                ) {
                    it()
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SnackbarHost(
    state: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (SnackbarData, Snackbar.Variant, Int?, String?) -> Unit = {
        data, variant, icon, caption ->
        Snackbar(
            data = data,
            variant = variant,
            caption = caption,
            icon = if (icon != null) painterResource(id = icon) else null
        )
    }
) {
    androidx.compose.material.SnackbarHost(
        hostState = state.materialSnackbarHostState,
        modifier = modifier,
        snackbar = {
            snackbar(it, state.variant, state.iconId, state.caption)
        }
    )
}

@Stable
@OptIn(ExperimentalMaterialApi::class)
class SnackbarHostState {
    val materialSnackbarHostState = MaterialSnackbarHostState()

    var variant: Snackbar.Variant = Snackbar.Variant.Default
        private set

    @IdRes
    var iconId: Int? = null
        private set
    var caption: String? = null
        private set

    suspend fun showSnackbar(
        message: String,
        variant: Snackbar.Variant = Snackbar.Variant.Default,
        @IdRes iconId: Int? = null,
        caption: String? = null,
        actionLabel: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short
    ): SnackbarResult {
        this.variant = variant
        this.iconId = iconId
        this.caption = caption
        return materialSnackbarHostState.showSnackbar(message, actionLabel, duration)
    }
}

private const val TEXT_MAX_LINE = 2
private const val CAPTION_MAX_LINE = 1
private const val ACTION_MAX_WIDTH = 88

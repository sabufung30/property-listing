package com.sabufung.app.ds.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sabufung.app.ds.theme.DsColors
import com.sabufung.app.ds.theme.DsRadius
import com.sabufung.app.ds.theme.DsSizing
import com.sabufung.app.ds.theme.DsTypography

/**
 * @sample com.sabufung.app.ds.catalog.component.button.IconButtonScreen
 *
 * @param iconVector [ImageVector] to draw inside this Icon
 * @param checked whether IconButton is checked or unchecked
 * @param onCheckedChange callback to be invoked when IconButton is being clicked
 * @param label to show a text under the Icon
 * @param iconVectorChecked [ImageVector] to draw inside this Icon when is not checked
 * @param colorChecked [Color] icon color when IconButton is checked, default is DsColors.iconPrimary
 * @param enabled whether or not this IconButton will handle input events and appear enabled for semantics purposes
 */
@Composable
fun IconToggleButton(
    iconVector: Painter,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String = "",
    modifier: Modifier = Modifier,
    iconVectorChecked: Painter? = null,
    colorChecked: Color = DsColors.iconPrimary,
    enabled: Boolean = LocalButtonEnabled.current
) {
    val toggleable = if (enabled) {
        Modifier.toggleable(
            value = checked,
            onValueChange = {
                onCheckedChange(it)
            }
        )
    } else {
        Modifier
    }
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(DsRadius.medium)) // shape for the ripple
            .then(toggleable),
        shape = RoundedCornerShape(DsRadius.medium),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .padding(DsSizing.spacing4)
                .widthIn(min = BUTTON_MIN_WIDTH.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val icon = if (checked && iconVectorChecked != null) iconVectorChecked else iconVector
            val iconColor = if (!enabled) {
                DsColors.iconDisabled
            } else if (checked) {
                colorChecked
            } else {
                DsColors.iconPrimary
            }
            // TODO contentDescription
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(DsSizing.measure24),
                tint = iconColor
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

private const val BUTTON_MIN_WIDTH = 68
private const val LABEL_MAX_LINES = 2

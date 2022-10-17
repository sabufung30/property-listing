package com.sabufung.app.ds.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.sabufung.app.ds.theme.DsRadius

object CustomButton {
    data class Icon(
        val painter: Painter,
        val gravity: IconGravity,
        val tint: Color = Color.Unspecified,
        val contentDescription: String? = null,
        val position: Position = Position.RELATIVE,
    )

    data class Label(
        val text: String,
        val color: Color
    )

    data class Theme(
        val backgroundColor: Color,
        val disabledBackgroundColor: Color,
        val borderColor: Color,
        val disabledBorderColor: Color,
        val contentColor: Color,
        val disabledContentColor: Color
    )

    enum class Position {
        ABSOLUTE,
        RELATIVE
    }
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    label: CustomButton.Label,
    icon: CustomButton.Icon? = null,
    enabled: Boolean = true,
    sizeVariant: Button.SizeVariant = Button.SizeVariant.Medium,
    width: Button.Width = Button.Width.Unspecified,
    theme: CustomButton.Theme,
    onClick: () -> Unit
) {
    val labelStyle = sizeVariant.labelStyle.merge(
        TextStyle(
            color = label.color
        )
    )

    val backgroundColor = if (enabled) {
        theme.backgroundColor
    } else theme.disabledBackgroundColor

    OutlinedButton(
        onClick = {
            onClick()
        },
        modifier = modifier.then(width.modifier),
        enabled = enabled,
        shape = RoundedCornerShape(DsRadius.medium),
        border = BorderStroke(
            width = 1.dp,
            color = if (enabled) theme.borderColor else theme.disabledBorderColor
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = backgroundColor,
            contentColor = theme.contentColor,
            disabledContentColor = theme.disabledContentColor
        ),
        contentPadding = sizeVariant.padding
    ) {
        if (icon != null) {
            ButtonContent(
                text = label.text,
                textStyle = labelStyle,
                iconVector = icon.painter,
                iconGravity = icon.gravity,
                iconTint = icon.tint,
                iconSize = sizeVariant.iconSize,
                iconPosition = icon.position,
                iconContentDescription = icon.contentDescription
            )
        } else {
            ButtonContent(
                text = label.text,
                textStyle = labelStyle
            )
        }
    }
}

package com.sabufung.app.ds.component.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.sabufung.app.ds.theme.DsColors
import com.sabufung.app.ds.theme.DsRadius

@Composable
fun FlatButton(
    text: String,
    modifier: Modifier = Modifier,
    iconVector: Painter? = null,
    iconGravity: IconGravity = IconGravity.Start,
    enable: Boolean = LocalButtonEnabled.current,
    sizeVariant: Button.SizeVariant = Button.SizeVariant.Medium,
    width: Button.Width = Button.Width.Unspecified,
    onClick: (() -> Unit)
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.then(width.modifier),
        enabled = enable,
        shape = RoundedCornerShape(DsRadius.medium),
        colors = ButtonDefaults.textButtonColors(
            contentColor = DsColors.textAttention,
            disabledContentColor = DsColors.textDisabled
        ),
        contentPadding = sizeVariant.padding
    ) {
        if (iconVector != null) {
            ButtonContent(
                text = text,
                textStyle = sizeVariant.labelStyle,
                iconVector = iconVector,
                iconGravity = iconGravity,
                iconSize = sizeVariant.iconSize
            )
        } else {
            ButtonContent(text = text, textStyle = sizeVariant.labelStyle)
        }
    }
}

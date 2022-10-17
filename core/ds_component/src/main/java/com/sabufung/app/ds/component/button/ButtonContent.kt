package com.sabufung.app.ds.component.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.sabufung.app.ds.theme.DsIcons
import com.sabufung.app.ds.theme.DsSizing
import com.sabufung.app.ds.theme.DsTypography

@Composable
internal fun ButtonContent(text: String, textStyle: TextStyle, modifier: Modifier = Modifier) {
    Text(
        text = text,
        overflow = TextOverflow.Ellipsis,
        maxLines = BUTTON_TEXT_MAX_LINE,
        textAlign = TextAlign.Center,
        style = textStyle,
        modifier = modifier
    )
}

@Composable
internal fun ButtonContent(
    text: String,
    textStyle: TextStyle,
    iconVector: Painter,
    iconGravity: IconGravity,
    iconSize: Dp,
    iconTint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    iconPosition: CustomButton.Position = CustomButton.Position.RELATIVE,
    iconContentDescription: String? = null
) {
    ConstraintLayout {
        val (button, icon) = createRefs()
        when (iconGravity) {
            IconGravity.Start -> {
                when (iconPosition) {
                    CustomButton.Position.RELATIVE -> {
                        Icon(
                            painter = iconVector,
                            contentDescription = iconContentDescription,
                            modifier = Modifier.size(iconSize).constrainAs(icon) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(button.start)
                            }.padding(end = DsSizing.spacing4),
                            tint = iconTint
                        )

                        ButtonContent(
                            text = text,
                            textStyle = textStyle,
                            modifier = Modifier.constrainAs(button) {
                                start.linkTo(icon.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                        )
                    }
                    CustomButton.Position.ABSOLUTE -> {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = iconVector,
                                contentDescription = iconContentDescription,
                                modifier = Modifier
                                    .size(iconSize)
                                    .padding(end = DsSizing.spacing4),
                                tint = iconTint
                            )

                            ButtonContent(
                                text = text,
                                textStyle = textStyle,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            }
            IconGravity.End -> {
                ButtonContent(
                    text = text,
                    textStyle = textStyle,
                    modifier = Modifier.constrainAs(button) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(icon.start)
                    }
                )

                // TODO contentDescription
                Icon(
                    painter = iconVector,
                    contentDescription = null,
                    modifier = Modifier.size(iconSize).constrainAs(icon) {
                        start.linkTo(button.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }.padding(start = DsSizing.spacing4),
                    tint = iconTint
                )
            }
        }
    }
}

enum class IconGravity { Start, End }

internal const val BUTTON_TEXT_MAX_LINE = 2

@Preview("ButtonContentPreview")
@Composable
fun ButtonContentPreview() {
    ButtonContent(text = "ボタン ラベル", textStyle = DsTypography.mediumStrong)
}

@Preview("ButtonContentWithStartIconPreview")
@Composable
fun ButtonContentWithStartIconPreview() {
    ButtonContent(
        text = "ボタン ラベル",
        textStyle = DsTypography.mediumStrong,
        iconVector = DsIcons.homeFilled,
        iconGravity = IconGravity.Start,
        iconSize = DsSizing.measure16
    )
}

@Preview("ButtonContentWithEndIconPreview")
@Composable
fun ButtonContentWithEndIconPreview() {
    ButtonContent(
        text = "ボタン ラベル",
        textStyle = DsTypography.mediumStrong,
        iconVector = DsIcons.homeFilled,
        iconGravity = IconGravity.End,
        iconSize = DsSizing.measure16
    )
}

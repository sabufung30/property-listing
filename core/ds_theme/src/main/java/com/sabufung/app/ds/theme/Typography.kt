package com.sabufung.app.ds.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

interface Typography : TypographyTokenContract {
    // material theme
    val materialTypography: androidx.compose.material.Typography
}

internal class DsObservableTypography(
    // material theme
    override val materialTypography: androidx.compose.material.Typography,

    override val x2Large: TextStyle = buildTextStyle(24),
    override val xLarge: TextStyle = buildTextStyle(20),
    override val large: TextStyle = buildTextStyle(17),
    override val medium: TextStyle = buildTextStyle(15),
    override val small: TextStyle = buildTextStyle(14),
    override val xSmall: TextStyle = buildTextStyle(12),
    override val x2Small: TextStyle = buildTextStyle(11),
    override val x3Small: TextStyle = buildTextStyle(10),

    override val x2LargeStrong: TextStyle = x2Large.merge(StrongTextStyle),
    override val xLargeStrong: TextStyle = xLarge.merge(StrongTextStyle),
    override val largeStrong: TextStyle = large.merge(StrongTextStyle),
    override val mediumStrong: TextStyle = medium.merge(StrongTextStyle),
    override val smallStrong: TextStyle = small.merge(StrongTextStyle),
    override val xSmallStrong: TextStyle = xSmall.merge(StrongTextStyle),
    override val x2SmallStrong: TextStyle = x2Small.merge(StrongTextStyle),
    override val x3SmallStrong: TextStyle = x3Small.merge(StrongTextStyle)
) : Typography

fun dsTypography(): Typography = DsObservableTypography(
    materialTypography = androidx.compose.material.Typography()
)

private val StrongTextStyle = TextStyle(fontWeight = FontWeight.Bold)
private const val LINE_HEIGHT_MULTIPLIER = 1.4
private fun buildTextStyle(fontSize: Int) = TextStyle(
    fontSize = fontSize.sp,
    lineHeight = (fontSize * LINE_HEIGHT_MULTIPLIER).sp
)

// default typography
internal val LocalDsTypography = staticCompositionLocalOf { dsTypography() }

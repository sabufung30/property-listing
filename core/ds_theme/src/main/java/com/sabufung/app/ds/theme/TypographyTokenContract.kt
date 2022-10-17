package com.sabufung.app.ds.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle

@Immutable
interface TypographyTokenContract {
    val x2Large: TextStyle
    val xLarge: TextStyle
    val large: TextStyle
    val medium: TextStyle
    val small: TextStyle
    val xSmall: TextStyle
    val x2Small: TextStyle
    val x3Small: TextStyle

    val x2LargeStrong: TextStyle
    val xLargeStrong: TextStyle
    val largeStrong: TextStyle
    val mediumStrong: TextStyle
    val smallStrong: TextStyle
    val xSmallStrong: TextStyle
    val x2SmallStrong: TextStyle
    val x3SmallStrong: TextStyle
}

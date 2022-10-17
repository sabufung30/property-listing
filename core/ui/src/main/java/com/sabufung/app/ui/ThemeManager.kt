package com.sabufung.app.ui

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.mutableStateOf
import com.sabufung.app.ds.theme.dsDarkColorPalette
import com.sabufung.app.ds.theme.dsLightColorPalette

class ThemeManager(
    isDarkMode: Boolean = false
) {
    val currentColors =
        if (isDarkMode) dsDarkColorPalette() else dsLightColorPalette()

    val textSelectionColors = TextSelectionColors(
        handleColor = currentColors.iconLink,
        backgroundColor = currentColors.iconLink.copy(alpha = TEXT_SELECTION_BACKGROUND_ALPHA)
    )

    companion object {
        private const val TEXT_SELECTION_BACKGROUND_ALPHA = 0.2f
    }
}


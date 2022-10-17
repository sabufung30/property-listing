package com.sabufung.app.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.sabufung.app.ds.theme.DsTheme

@Composable
fun MadAppTheme(
    children: @Composable () -> Unit
) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val themeManager = remember(isSystemInDarkTheme) {
        ThemeManager(isSystemInDarkTheme)
    }

    DsTheme(
        colors = themeManager.currentColors,
    ) {
        CompositionLocalProvider(
            LocalTextSelectionColors provides themeManager.textSelectionColors,
            content = children
        )
    }
}

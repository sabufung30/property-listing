package com.sabufung.app.ds.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DsTheme(
    colors: DsColorPalette = dsLightColorPalette(),
    typography: Typography = dsTypography(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalDsColor provides colors) {
        MaterialTheme(
            colors = colors.materialColorPalette,
            typography = typography.materialTypography,
        ) {
            val controller = rememberSystemUiController()

            SideEffect {
                controller.setStatusBarColor(
                    color = colors.backgroundPrimary,
                    darkIcons = colors.materialColorPalette.isLight
                )
            }
            content()
        }
    }
}

val DsColors: DsColorPalette
    @Composable
    @ReadOnlyComposable
    get() = LocalDsColor.current

val DsTypography: Typography
    @Composable
    @ReadOnlyComposable
    get() = LocalDsTypography.current

val DsSizing: Sizing = Sizing

val DsRadius: Radius = Radius

val DsBorders: Borders = Borders

val DsShadows: Shadows = Shadows

val DsIcons: Icons = Icons

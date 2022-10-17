package com.sabufung.app.ds.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// merge Material Theme color palette with Design System color token
interface DsColorPalette : ColorTokenContract {
    // material theme
    val materialColorPalette: Colors
}

internal class DsObservableColorPalette(
    // material theme
    override val materialColorPalette: Colors,

    // design system
    val colorTokens: ColorTokenContract,

    override val backgroundAccent: Color = colorTokens.backgroundAccent,
    override val backgroundAccentActive: Color = colorTokens.backgroundAccentActive,
    override val backgroundAccentHighlight: Color = colorTokens.backgroundAccentHighlight,
    override val backgroundAccentThin: Color = colorTokens.backgroundAccentThin,
    override val backgroundAccentThinActive: Color = colorTokens.backgroundAccentThinActive,
    override val backgroundAccentThinHighlight: Color = colorTokens.backgroundAccentThinHighlight,
    override val backgroundAttention: Color = colorTokens.backgroundAttention,
    override val backgroundAttentionActive: Color = colorTokens.backgroundAttentionActive,
    override val backgroundAttentionHighlight: Color = colorTokens.backgroundAttentionHighlight,
    override val backgroundAttentionThin: Color = colorTokens.backgroundAttentionThin,
    override val backgroundAttentionThinActive: Color = colorTokens.backgroundAttentionThinActive,
    override val backgroundAttentionThinHighlight: Color = colorTokens.backgroundAttentionThinHighlight,
    override val backgroundDisabled: Color = colorTokens.backgroundDisabled,
    override val backgroundPrimary: Color = colorTokens.backgroundPrimary,
    override val backgroundPrimaryActive: Color = colorTokens.backgroundPrimaryActive,
    override val backgroundPrimaryHighlight: Color = colorTokens.backgroundPrimaryHighlight,
    override val backgroundSecondary: Color = colorTokens.backgroundSecondary,
    override val backgroundSecondaryActive: Color = colorTokens.backgroundSecondaryActive,
    override val backgroundSecondaryHighlight: Color = colorTokens.backgroundSecondaryHighlight,
    override val backgroundSuccess: Color = colorTokens.backgroundSuccess,
    override val backgroundSuccessActive: Color = colorTokens.backgroundSuccessActive,
    override val backgroundSuccessHighlight: Color = colorTokens.backgroundSuccessHighlight,
    override val backgroundTertiary: Color = colorTokens.backgroundTertiary,
    override val backgroundTertiaryActive: Color = colorTokens.backgroundTertiaryActive,
    override val backgroundTertiaryHighlight: Color = colorTokens.backgroundTertiaryHighlight,
    override val borderAccent: Color = colorTokens.borderAccent,
    override val borderAccentActive: Color = colorTokens.borderAccentActive,
    override val borderAccentHighlight: Color = colorTokens.borderAccentHighlight,
    override val borderAttention: Color = colorTokens.borderAttention,
    override val borderAttentionActive: Color = colorTokens.borderAttentionActive,
    override val borderAttentionHighlight: Color = colorTokens.borderAttentionHighlight,
    override val borderDisabled: Color = colorTokens.borderDisabled,
    override val borderPrimary: Color = colorTokens.borderPrimary,
    override val borderPrimaryActive: Color = colorTokens.borderPrimaryActive,
    override val borderPrimaryHighlight: Color = colorTokens.borderPrimaryHighlight,
    override val borderSecondary: Color = colorTokens.borderSecondary,
    override val borderSecondaryActive: Color = colorTokens.borderSecondaryActive,
    override val borderSecondaryHighlight: Color = colorTokens.borderSecondaryHighlight,
    override val borderSuccess: Color = colorTokens.borderSecondaryActive,
    override val borderSuccessActive: Color = colorTokens.borderSuccessActive,
    override val borderSuccessHighlight: Color = colorTokens.borderSuccessHighlight,
    override val iconAccent: Color = colorTokens.iconAccent,
    override val iconAccentActive: Color = colorTokens.iconAccentActive,
    override val iconAccentHighlight: Color = colorTokens.iconAccentHighlight,
    override val iconAttention: Color = colorTokens.iconAttention,
    override val iconAttentionActive: Color = colorTokens.iconAttentionActive,
    override val iconAttentionHighlight: Color = colorTokens.iconAttentionHighlight,
    override val iconDisabled: Color = colorTokens.iconDisabled,
    override val iconInverse: Color = colorTokens.iconInverse,
    override val iconInverseActive: Color = colorTokens.iconInverseActive,
    override val iconInverseHighlight: Color = colorTokens.iconInverseHighlight,
    override val iconLink: Color = colorTokens.iconLink,
    override val iconLinkActive: Color = colorTokens.iconLinkActive,
    override val iconLinkHighlight: Color = colorTokens.iconLinkHighlight,
    override val iconPrimary: Color = colorTokens.iconPrimary,
    override val iconPrimaryActive: Color = colorTokens.iconPrimaryActive,
    override val iconPrimaryHighlight: Color = colorTokens.iconPrimaryHighlight,
    override val iconSecondary: Color = colorTokens.iconSecondary,
    override val iconSecondaryActive: Color = colorTokens.iconSecondaryActive,
    override val iconSecondaryHighlight: Color = colorTokens.iconSecondaryHighlight,
    override val iconSuccess: Color = colorTokens.iconSuccess,
    override val iconSuccessActive: Color = colorTokens.iconSuccessActive,
    override val iconSuccessHighlight: Color = colorTokens.iconSuccessHighlight,
    override val iconDecorativeYellow: Color = colorTokens.iconDecorativeYellow,
    override val iconDecorativeYellowActive: Color = colorTokens.iconDecorativeYellowActive,
    override val iconDecorativeYellowHighlight: Color = colorTokens.iconDecorativeYellowHighlight,
    override val overlayStrong: Color = colorTokens.overlayStrong,
    override val overlayMiddle: Color = colorTokens.overlayMiddle,
    override val overlayWeak: Color = colorTokens.overlayWeak,
    override val staticBlack: Color = colorTokens.staticBlack,
    override val staticClear: Color = colorTokens.staticClear,
    override val staticWhite: Color = colorTokens.staticWhite,
    override val textAccent: Color = colorTokens.textAccent,
    override val textAccentActive: Color = colorTokens.textAccentActive,
    override val textAccentHighlight: Color = colorTokens.textAccentHighlight,
    override val textAttention: Color = colorTokens.textAttention,
    override val textAttentionActive: Color = colorTokens.textAttentionActive,
    override val textAttentionHighlight: Color = colorTokens.textAttentionHighlight,
    override val textDisabled: Color = colorTokens.textDisabled,
    override val textInverse: Color = colorTokens.textInverse,
    override val textInverseActive: Color = colorTokens.textInverseActive,
    override val textInverseHighlight: Color = colorTokens.textInverseHighlight,
    override val textLink: Color = colorTokens.textLink,
    override val textLinkActive: Color = colorTokens.textLinkActive,
    override val textLinkHighlight: Color = colorTokens.textLinkHighlight,
    override val textPlaceholder: Color = colorTokens.textPlaceholder,
    override val textPrimary: Color = colorTokens.textPrimary,
    override val textPrimaryActive: Color = colorTokens.textPrimaryActive,
    override val textPrimaryHighlight: Color = colorTokens.textPrimaryHighlight,
    override val textSecondary: Color = colorTokens.textSecondary,
    override val textSecondaryActive: Color = colorTokens.textSecondaryActive,
    override val textSecondaryHighlight: Color = colorTokens.textSecondaryHighlight,
    override val textSuccess: Color = colorTokens.textSuccess,
    override val textSuccessActive: Color = colorTokens.textSuccessActive,
    override val textSuccessHighlight: Color = colorTokens.textSuccessHighlight,
    override val transactionInformationBubbleBackground: Color = colorTokens.transactionInformationBubbleBackground
) : DsColorPalette

fun dsLightColorPalette(
    colors: ColorTokenContract = com.sabufung.app.ds.theme.light.Colors()
): DsColorPalette = DsObservableColorPalette(
    materialColorPalette = lightColors(
        // surface color will affect the elevation color for component which use Surface as base
        surface = colors.backgroundPrimary,
        background = colors.backgroundPrimary,
        onBackground = colors.textPrimary,
        onSurface = colors.textPrimary
    ),
    colorTokens = colors
)

fun dsDarkColorPalette(
    colors: ColorTokenContract = com.sabufung.app.ds.theme.dark.Colors()
): DsColorPalette = DsObservableColorPalette(
    materialColorPalette = darkColors(
        // surface color will affect the elevation color for component which use Surface as base
        surface = colors.backgroundPrimary,
        background = colors.backgroundPrimary,
        onBackground = colors.textPrimary,
        onSurface = colors.textPrimary
    ),
    colorTokens = colors
)

// default colors
internal val LocalDsColor = staticCompositionLocalOf { dsLightColorPalette() }

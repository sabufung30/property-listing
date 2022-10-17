package com.sabufung.app.ds.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.sabufung.app.ds.theme.DsSizing
import com.sabufung.app.ds.theme.DsTypography

object Button {
    sealed class SizeVariant {
        @get:Composable
        abstract val labelStyle: TextStyle

        @get:Composable
        abstract val iconSize: Dp

        @get:Composable
        abstract val padding: PaddingValues

        object Large : SizeVariant() {
            override val labelStyle: TextStyle
                @Composable
                get() = DsTypography.largeStrong

            override val iconSize: Dp
                @Composable
                get() = DsSizing.measure24

            override val padding: PaddingValues
                @Composable
                get() = PaddingValues(DsSizing.spacing16)
        }

        object Medium : SizeVariant() {
            override val labelStyle: TextStyle
                @Composable
                get() = DsTypography.mediumStrong

            override val iconSize: Dp
                @Composable
                get() = DsSizing.measure20

            override val padding: PaddingValues
                @Composable
                get() = PaddingValues(
                    start = DsSizing.spacing16,
                    top = DsSizing.spacing12,
                    end = DsSizing.spacing16,
                    bottom = DsSizing.spacing12
                )
        }

        object Small : SizeVariant() {
            override val labelStyle: TextStyle
                @Composable
                get() = DsTypography.xSmallStrong

            override val iconSize: Dp
                @Composable
                get() = DsSizing.measure12

            override val padding: PaddingValues
                @Composable
                get() = PaddingValues(DsSizing.spacing8)
        }
    }

    sealed class Width {
        abstract val modifier: Modifier

        /**
         * use [Modifier.weight] for balancing horizontal weight when it has sibling components
         */
        object Fluid : Width() {
            override val modifier: Modifier
                @get:Composable
                get() {
                    return Modifier.fillMaxWidth()
                }
        }

        class Specified(val width: Dp) : Width() {
            override val modifier: Modifier
                @get:Composable
                get() = Modifier.width(width = width)
        }

        object Unspecified : Width() {
            override val modifier: Modifier
                @get:Composable
                get() = Modifier.wrapContentWidth()
        }
    }
}

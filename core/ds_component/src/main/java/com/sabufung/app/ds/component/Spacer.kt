package com.sabufung.app.ds.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.sabufung.app.ds.theme.DsSizing

@Composable
fun VerticalSpacer(
    size: Dp = DsSizing.spacing16
) {
    Spacer(modifier = Modifier.requiredHeight(size))
}

@Composable
fun HorizontalSpacer(
    size: Dp = DsSizing.spacing16
) {
    Spacer(modifier = Modifier.requiredWidth(size))
}

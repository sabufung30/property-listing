package com.sabufung.app.ds.theme

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.unit.dp

object Radius {
    val small: CornerSize = CornerSize(2.dp)
    val medium: CornerSize = CornerSize(4.dp)
    val large: CornerSize = CornerSize(8.dp)
    val xlarge: CornerSize = CornerSize(13.dp)
    val full: CornerSize = CornerSize(percent = 50) // 50% makes the corner circled
}

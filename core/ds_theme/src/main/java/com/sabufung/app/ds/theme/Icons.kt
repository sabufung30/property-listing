package com.sabufung.app.ds.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

@Suppress("unused")
object Icons {
    val likeOutlined: Painter
        @Composable
        get() = painterResource(id = IconIds.likeOutlined)
    val heartFilled: Painter
        @Composable
        get() = painterResource(id = IconIds.heartFilled)
    val homeFilled: Painter
        @Composable
        get() = painterResource(id = IconIds.homeFilled)

    val homeOutlined: Painter
        @Composable
        get() = painterResource(id = IconIds.homeOutlined)
    val personFilled: Painter
        @Composable
        get() = painterResource(id = IconIds.personFilled)

    val personOutlined: Painter
        @Composable
        get() = painterResource(id = IconIds.personOutlined)
    val bookmarkOutlined: Painter
        @Composable
        get() = painterResource(id = IconIds.bookmarkOutlined)

    val bookmarkFilled: Painter
        @Composable
        get() = painterResource(id = IconIds.bookmarkFilled)

    val locationFilled: Painter
        @Composable
        get() = painterResource(id = IconIds.locationFilled)
}

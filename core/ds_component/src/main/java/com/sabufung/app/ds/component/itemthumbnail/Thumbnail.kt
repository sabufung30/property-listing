package com.sabufung.app.ds.component.itemthumbnail

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.sabufung.app.ds.theme.DsColors

/**
 *
 * @param painter - The [Painter] to display
 * @param contentDescription - text used by accessibility services to describe what this image
 * represents. This should always be provided unless this image is used for decorative purposes,
 * and does not represent a meaningful action that a user can take. This text should be
 * localized, such as by using [androidx.compose.ui.res.stringResource] or similar
 * @param modifier - [Modifier]
 */
@Composable
fun Thumbnail(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        alignment = Alignment.Center,
        contentScale = ContentScale.Fit
    )
}

/**
 * @param data - The data to load, usually an url
 * @param contentDescription - text used by accessibility services to describe what this image
 * represents. This should always be provided unless this image is used for decorative purposes,
 * and does not represent a meaningful action that a user can take. This text should be
 * localized, such as by using [androidx.compose.ui.res.stringResource] or similar
 * @param fadeIn - Whether to run a fade-in animation when images are successfully loaded. Default: true
 */
@OptIn(ExperimentalCoilApi::class)
@Composable
fun Thumbnail(
    data: Any?,
    contentDescription: String?,
    fadeIn: Boolean = true,
    contentScale: ContentScale = ContentScale.Fit
) {
    if (data != null) {
        val painter = rememberImagePainter(
            data = data,
        )
        Crossfade(
            targetState = painter.state,
            animationSpec = if (fadeIn) tween() else {
                // the point is to avoid the placeholder state when image is already cached,
                // this should work more smoothly when LazyColumn supports preloading nearby
                // offscreen content
                snap()
            }
        ) { state ->
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f, matchHeightConstraintsFirst = true),
                alignment = Alignment.Center,
                contentScale = contentScale,
            )
            if (state !is ImagePainter.State.Success) {
                ThumbnailPlaceholder()
            }
        }
    } else {
        ThumbnailPlaceholder()
    }
}


/**
 * For loading and error state
 */
@Composable
fun ThumbnailPlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f)
            .background(DsColors.backgroundSecondary)
    )
}

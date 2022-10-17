package com.sabufung.feature.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sabufung.app.ds.component.TopNavigation
import com.sabufung.app.ds.component.button.IconButton
import com.sabufung.app.ds.component.itemthumbnail.Thumbnail
import com.sabufung.app.ds.theme.DsColors
import com.sabufung.app.ds.theme.DsIcons
import com.sabufung.app.ds.theme.DsRadius
import com.sabufung.app.ds.theme.DsShadows
import com.sabufung.app.ds.theme.DsSizing
import com.sabufung.app.ds.theme.DsTypography
import com.sabufung.app.ui.toPriceFormat
import com.sabufung.domain.model.property.toFullAddress
import com.sabufung.feature.home.R
import com.sabufung.feature.home.presentation.ListingPageScreenTestTag.EMPTY_DATA_TEXT_TAG
import com.sabufung.feature.home.presentation.ListingPageScreenTestTag.PROPERTY_LIST_TAG

@Composable
fun ListingPageScreen(
    viewModel: ListingViewModel,
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopNavigation(
                title = stringResource(id = R.string.app_name),
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = DsColors.backgroundPrimary
        ) {
            if (state.properties.loading) {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(modifier = Modifier.size(DsSizing.measure48))
                }
            } else {
                if (state.properties.data!!.isNotEmpty()) {
                    LazyColumn(modifier = Modifier.fillMaxHeight().testTag(PROPERTY_LIST_TAG)) {
                        state.properties.data!!.map { property ->
                            item {
                                PropertyCard(
                                    title = property.title,
                                    images = property.images,
                                    price = property.price,
                                    address = property.address.toFullAddress(),
                                    isBookmarked = property.isBookmarked,
                                    onBookmarkClick = {
                                        viewModel.onEvent(ListingEvent.OnBookmarkItem(property.id,
                                            property.isBookmarked))
                                    }
                                ) {
                                    viewModel.onEvent(ListingEvent.OnItemClick(property.id))
                                }
                            }
                        }
                    }
                } else {
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(modifier = Modifier.testTag(EMPTY_DATA_TEXT_TAG), text = "Empty Data")
                    }
                }
            }
        }
    }
}

@Composable
fun PropertyCard(
    images: List<String>,
    title: String,
    address: String,
    price: Double,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(DsSizing.spacing16)
            .clickable {
                onClick()
            },
        elevation = DsShadows.secondFloor,
        shape = RoundedCornerShape(DsRadius.xlarge)
    ) {
        Box() {
            Column(
            ) {
                Thumbnail(
                    data = if (images.isEmpty()) "" else images[0],
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                PropertyMetadata(title = title,
                    address = address,
                    price = price,
                    isBookmarked = isBookmarked) {
                    onBookmarkClick()
                }
            }

        }
    }
}

@Composable
fun PropertyMetadata(
    modifier: Modifier = Modifier,
    title: String,
    address: String,
    price: Double,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
) {
    Column(modifier = modifier.padding(DsSizing.spacing16),
        verticalArrangement = Arrangement.spacedBy(
            DsSizing.spacing2)) {
        Text(title, style = DsTypography.largeStrong)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(
                    text = price.toPriceFormat(),
                    style = DsTypography.x2LargeStrong,
                    color = DsColors.textSuccessHighlight
                )
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(DsSizing.spacing4)) {
                    Icon(
                        painter = DsIcons.locationFilled,
                        contentDescription = null,
                        modifier = Modifier.size(DsSizing.measure20),
                        tint = Color.Red
                    )
                    Text(address,
                        style = DsTypography.small,
                        color = DsColors.backgroundTertiaryHighlight)
                }
            }
            IconButton(
                iconVector = if (isBookmarked) DsIcons.heartFilled else DsIcons.likeOutlined,
                variant = IconButton.Variant.Tertiary,
                onClick = {
                    onBookmarkClick()
                },
                tint = Color.Red
            )
        }
    }

}

@Preview
@Composable
fun PreviewPropertyCard() {
    PropertyCard(images = listOf(),
        title = "Maison moderne - Annonce exemplaire",
        address = "Musterstrasse 999, 2406 Zurich",
        price = 193000.0,
        isBookmarked = true) {
    }
}

object ListingPageScreenTestTag {
    const val EMPTY_DATA_TEXT_TAG = "EmptyText"
    const val PROPERTY_LIST_TAG = "PropertyList"
}

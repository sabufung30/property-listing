package com.sabufung.feature.home.presentation

sealed class ListingEvent {
    data class OnItemClick(val id: String) : ListingEvent()
    data class OnBookmarkItem(val id: String,
                             val bookmarkValue: Boolean) : ListingEvent()
}
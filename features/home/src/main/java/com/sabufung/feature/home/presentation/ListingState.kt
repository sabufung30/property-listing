package com.sabufung.feature.home.presentation

import com.sabufung.data.DomainError
import com.sabufung.data.LoadingState
import com.sabufung.domain.model.property.Property

data class ListingState (
    val properties: LoadingState<List<Property>, DomainError> = LoadingState(),
    // Workaround to trigger recomposition from LazyColumn
    val toggle: Boolean = false //TODO: Replace it with better solution
)

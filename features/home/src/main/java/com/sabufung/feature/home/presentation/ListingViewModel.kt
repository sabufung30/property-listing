package com.sabufung.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabufung.data.LoadingState
import com.sabufung.feature.home.domain.usecase.BookmarkPropertyUseCase
import com.sabufung.feature.home.domain.usecase.GetPropertiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ListingViewModel {
    val state: StateFlow<ListingState>
    val navigationAction: Flow<ListingNavigationAction>

    fun onEvent(event: ListingEvent)
}


@HiltViewModel
class ListingViewModelImpl @Inject constructor(
    private val bookmarkPropertyUseCase: BookmarkPropertyUseCase,
    private val getPropertiesUseCase: GetPropertiesUseCase,
) : ViewModel(), ListingViewModel {

    override val state = MutableStateFlow(ListingState())

    override val navigationAction = MutableSharedFlow<ListingNavigationAction>()
    override fun onEvent(event: ListingEvent) {
        when (event) {
            is ListingEvent.OnItemClick -> navigateToItemDetails(event.id)
            is ListingEvent.OnBookmarkItem -> toggleBookmark(event.id, event.bookmarkValue)
        }
    }

    init {
        state.update {
            it.copy(properties = LoadingState(loading = true))
        }
        collectGetProperties()
    }

    private fun toggleBookmark(id: String, bookmarkValue: Boolean) {
        viewModelScope.launch {
            state.update { it.copy(toggle = true) }
            bookmarkPropertyUseCase(id, bookmarkValue.inc())
        }
    }

    private fun navigateToItemDetails(id: String) {
        //TODO item details navigation
//        viewModelScope.launch {
//            navigationAction.emit(ListingNavigationAction.NavigateToPropertyDetail(id))
//        }
    }

    private fun collectGetProperties() {
        viewModelScope.launch {
            getPropertiesUseCase().collect { properties ->
                state.update { it.copy(toggle = false,properties = LoadingState(data = properties, loading = false)) }
            }
        }
    }

}

operator fun Boolean.inc() = !this
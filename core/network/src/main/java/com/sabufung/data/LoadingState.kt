package com.sabufung.data

/**
 * Immutable data class that allows for loading, data, error and refreshing to be managed independently.
 *
 * This is useful for screens that want to show the last successful result while loading or a later
 * refresh has caused an error.
 *
 * Based from JetNews project in compose-samples.
 *
 * @param refreshing typically set by a user-triggered refresh, e.g., Pull-to-refresh.
 */
data class LoadingState<T, E>(
    val loading: Boolean = false,
    val error: E? = null,
    val data: T? = null,
    val refreshing: Boolean = false
) {
    /**
     * True if this contains an error
     */
    val hasError: Boolean
        get() = error != null

    class Builder<T, E>(state: LoadingState<T, E>) {
        var loading = state.loading
        var error = state.error
        var data = state.data
        var refreshing = state.refreshing

        fun build() = LoadingState(
            loading,
            error,
            data,
            refreshing
        )
    }

    companion object
}

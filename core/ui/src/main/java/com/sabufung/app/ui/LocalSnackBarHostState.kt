package com.sabufung.app.ui

import androidx.compose.runtime.compositionLocalOf
import com.sabufung.app.ds.component.SnackbarHostState

val LocalSnackbarHostState =
    compositionLocalOf<SnackbarHostState> { error("Unable to retrieve snackbar host state") }

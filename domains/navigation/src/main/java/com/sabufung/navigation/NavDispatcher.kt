package com.sabufung.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

/**
 * Inspired by:
 * https://itnext.io/simplifying-jetpack-navigation-between-top-level-destinations-using-dagger-hilt-3d918721d91e
 */
class NavDispatcher @Inject constructor() {

    private val _navigationCommands = MutableSharedFlow<NavController.() -> Unit>(replay = 1)
    val navigationCommands: SharedFlow<NavController.() -> Unit> = _navigationCommands

    operator fun invoke(command: NavController.() -> Unit) {
        dispatch(command)
    }

    private fun dispatch(command: NavController.() -> Unit) {
        _navigationCommands.tryEmit(command)
    }
}

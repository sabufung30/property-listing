package com.sabufung.navigation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.MainThread
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph
import androidx.navigation.NavOptions
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import java.io.Serializable

@SuppressLint("ComposableNaming")
@Composable
fun <T> Flow<T>.navigateOnEach(navigate: (T) -> Unit) {
    val flow = this
    LaunchedEffect(flow) {
        flow.onEach {
            navigate(it)
        }.collect()
    }
}

package com.sabufung.app.ds.theme

import androidx.compose.animation.core.CubicBezierEasing

@Suppress("unused")
object Easing {
    val linear = CubicBezierEasing(0f, 0f, 0f, 0f)
    val weakEaseInOut = CubicBezierEasing(0.37f, 0f, 0.63f, 1f)
    val weakEaseOut = CubicBezierEasing(0.61f, 1f, 0.88f, 1f)
    val weakEaseIn = CubicBezierEasing(0.12f, 0f, 0.39f, 0f)
    val middleEaseInOut = CubicBezierEasing(0.65f, 0f, 0.35f, 1f)
    val middleEaseOut = CubicBezierEasing(0.33f, 1f, 0.68f, 1f)
    val middleEaseIn = CubicBezierEasing(0.32f, 0f, 0.67f, 0f)
    val strongEaseInOut = CubicBezierEasing(0.83f, 0f, 0.17f, 1f)
    val strongEaseOut = CubicBezierEasing(0.22f, 1f, 0.36f, 1f)
    val strongEaseIn = CubicBezierEasing(0.64f, 0f, 0.78f, 0f)
}

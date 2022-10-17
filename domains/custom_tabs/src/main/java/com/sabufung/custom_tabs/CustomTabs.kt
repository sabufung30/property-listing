package com.sabufung.custom_tabs

import androidx.browser.customtabs.CustomTabsIntent

internal fun buildCustomTabs(builderAction: CustomTabsIntent.Builder.() -> Unit): CustomTabsIntent {
    return CustomTabsIntent.Builder().apply {
        builderAction(this)
    }.build()
}

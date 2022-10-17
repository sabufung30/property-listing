package com.sabufung.app.ui

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun Number.toPriceFormat(): String =
    (NumberFormat.getCurrencyInstance(Locale.US) as DecimalFormat)
        .apply { applyPattern("$#,###;- $#,###") }.format(this)

package com.sabufung.app.ds.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sabufung.app.ds.theme.DsBorders
import com.sabufung.app.ds.theme.DsColors

@Composable
fun Separator(
    modifier: Modifier = Modifier
) {
    Divider(
        modifier = modifier.then(Modifier.fillMaxWidth()),
        color = DsColors.borderPrimary,
        thickness = DsBorders.normal
    )
}

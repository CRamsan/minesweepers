package com.myapplication.common.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.PointerMatcher
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.onClick
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
actual fun TileButtonDrawable(
    text: String,
    mutableInteractionSource: MutableInteractionSource,
    onTileSelected: () -> Unit,
    onTileSelectedSecondary: () -> Unit
) {
    Text(
        text,
        modifier = Modifier
            .size(25.dp)
            .background(Color.Green)
            .onClick(
                interactionSource = mutableInteractionSource,
                matcher = PointerMatcher.mouse(PointerButton.Primary), // Right Mouse Button
            ) { onTileSelected() }
            .onClick(
                interactionSource = mutableInteractionSource,
                matcher = PointerMatcher.mouse(PointerButton.Secondary), // Right Mouse Button
            ) { onTileSelectedSecondary() }
    )
}

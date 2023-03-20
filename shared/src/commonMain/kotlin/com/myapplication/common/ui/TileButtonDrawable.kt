package com.myapplication.common.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

@Composable
internal expect fun TileButtonDrawable(
    text: String,
    mutableInteractionSource: MutableInteractionSource,
    onTileSelected: () -> Unit,
    onTileSelectedSecondary: () -> Unit
)
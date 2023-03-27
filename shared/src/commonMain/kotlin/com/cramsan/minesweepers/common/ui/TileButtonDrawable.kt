package com.cramsan.minesweepers.common.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

@Composable
internal expect fun TileButtonDrawable(
    image: ImageBitmap,
    onTileSelected: () -> Unit,
    onTileSelectedSecondary: () -> Unit
)

package com.cramsan.minesweepers.common.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
actual fun TileButtonDrawable(
    image: ImageBitmap,
    onTileSelected: () -> Unit,
    onTileSelectedSecondary: () -> Unit
) {
    Image(
        image,
        modifier = Modifier
            .size(Dimensions.TILE_SIZE)
            .onClick(
                matcher = PointerMatcher.mouse(PointerButton.Primary), // Right Mouse Button
            ) { onTileSelected() }
            .onClick(
                matcher = PointerMatcher.mouse(PointerButton.Secondary), // Right Mouse Button
            ) { onTileSelectedSecondary() },
        contentDescription = null,
        filterQuality = FilterQuality.None,
    )
}

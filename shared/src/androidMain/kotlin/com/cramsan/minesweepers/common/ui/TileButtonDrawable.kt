package com.cramsan.minesweepers.common.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal actual fun TileButtonDrawable(
    image: ImageBitmap,
    mutableInteractionSource: MutableInteractionSource,
    onTileSelected: () -> Unit,
    onTileSelectedSecondary: () -> Unit
) {
    Image(
        image,
        modifier = Modifier
            .size(25.dp)
            .background(Color.Green)
            .combinedClickable(
                mutableInteractionSource,
                null,
                onLongClick = { onTileSelectedSecondary() }
            ) { onTileSelected() },
        contentDescription = null,
    )
}

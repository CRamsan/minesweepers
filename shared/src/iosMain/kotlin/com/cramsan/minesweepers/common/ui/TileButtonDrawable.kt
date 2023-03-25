package com.cramsan.minesweepers.common.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import com.cramsan.minesweepers.common.ui.theme.Dimensions

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal actual fun TileButtonDrawable(
    image: ImageBitmap,
    onTileSelected: () -> Unit,
    onTileSelectedSecondary: () -> Unit
) {
    Image(
        image,
        modifier = Modifier
            .size(Dimensions.TILE_SIZE)
            .combinedClickable(
                onLongClick = { onTileSelectedSecondary() }
            ) { onTileSelected() },
        contentDescription = null,
        filterQuality = FilterQuality.None,
    )
}

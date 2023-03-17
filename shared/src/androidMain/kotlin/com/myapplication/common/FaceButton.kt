package com.myapplication.common

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

@Composable
internal actual fun FaceButton(
    faceButtonState: FaceButtonState,
    isTilePressed: Boolean,
    mutableInteractionSource: MutableInteractionSource,
    onRestartSelected: () -> Unit
) {
}

@Composable
actual fun TileButtonDrawable(
    imageBitmap: ImageBitmap,
    mutableInteractionSource: MutableInteractionSource,
    onTileSelected: () -> Unit,
    onTileSelectedSecondary: () -> Unit
) {
}
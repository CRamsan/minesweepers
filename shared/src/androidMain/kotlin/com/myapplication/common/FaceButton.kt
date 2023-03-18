package com.myapplication.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap

@Composable
actual fun TileButtonDrawable(
    imageBitmap: ImageBitmap,
    mutableInteractionSource: MutableInteractionSource,
    onTileSelected: () -> Unit,
    onTileSelectedSecondary: () -> Unit
) {
    Image(
        imageBitmap,
        contentDescription = "",
        modifier = Modifier.clickable { onTileSelected() }
    )
}

@Composable
internal actual fun FaceButton(
    faceButtonState: FaceButtonState,
    isTilePressed: Boolean,
    mutableInteractionSource: MutableInteractionSource,
    assets: Assets,
    onRestartSelected: () -> Unit
) {
    val isPressed by mutableInteractionSource.collectIsPressedAsState()

    val coalescedState = if (faceButtonState == FaceButtonState.DEAD || faceButtonState == FaceButtonState.WON) {
        faceButtonState
    } else if (isPressed) {
        FaceButtonState.PRESSED
    } else if (isTilePressed) {
        FaceButtonState.WORRIED
    } else {
        faceButtonState
    }

    Image(
        coalescedState.toImageBitmap(assets),
        contentDescription = "",
        modifier = Modifier.clickable { onRestartSelected() }
    )
}

package com.myapplication.common

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.PointerButton
import com.myapplication.common.FaceButtonState
import com.myapplication.common.toImageBitmap

@OptIn(ExperimentalFoundationApi::class)
@Composable
actual fun TileButtonDrawable(
    imageBitmap: ImageBitmap,
    mutableInteractionSource: MutableInteractionSource,
    onTileSelected: () -> Unit,
    onTileSelectedSecondary: () -> Unit,
) {
    Image(
        imageBitmap,
        contentDescription = "",
        modifier = Modifier.onClick(
            interactionSource = mutableInteractionSource,
            matcher = PointerMatcher.mouse(PointerButton.Primary), // Right Mouse Button
        ) { onTileSelected() }
        .onClick(
            interactionSource = mutableInteractionSource,
            matcher = PointerMatcher.mouse(PointerButton.Secondary), // Right Mouse Button
        ) { onTileSelectedSecondary() }
    )
}

@Composable
internal actual fun FaceButton(
    faceButtonState: FaceButtonState,
    isTilePressed: Boolean,
    mutableInteractionSource: MutableInteractionSource,
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
        coalescedState.toImageBitmap(),
        contentDescription = "",
        modifier = Modifier.clickable { onRestartSelected() }
    )
}
package com.cramsan.minesweepers.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import com.cramsan.minesweepers.common.game.Status
import com.cramsan.minesweepers.common.ui.theme.Dimensions

@Composable
internal fun ResetButton(
    status: Status,
    onRestartSelected: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val faceButtonState = status.toFaceButtonState()

    val coalescedState = if (faceButtonState == FaceButtonState.DEAD || faceButtonState == FaceButtonState.WON) {
        faceButtonState
    } else if (isPressed) {
        FaceButtonState.PRESSED
    } else {
        faceButtonState
    }

    Image(
        coalescedState.toImageBitmap(),
        contentDescription = "",
        filterQuality = FilterQuality.None,
        modifier = Modifier
            .size(Dimensions.BUTTON_SIZE)
            .clickable { onRestartSelected() }
    )
}

private fun FaceButtonState.toImageBitmap(): ImageBitmap = when(this) {
    FaceButtonState.NORMAL -> Assets.buttonNormal()
    FaceButtonState.DEAD -> Assets.buttonDead()
    FaceButtonState.PRESSED -> Assets.buttonPressed()
    FaceButtonState.WON -> Assets.buttonWon()
}

private fun Status.toFaceButtonState() = when (this) {
    Status.NORMAL -> FaceButtonState.NORMAL
    Status.WON -> FaceButtonState.WON
    Status.LOST -> FaceButtonState.DEAD
}

internal enum class FaceButtonState {
    NORMAL,
    DEAD,
    PRESSED,
    WON,
}

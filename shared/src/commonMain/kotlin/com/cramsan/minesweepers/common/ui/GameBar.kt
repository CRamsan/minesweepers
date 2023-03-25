package com.cramsan.minesweepers.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cramsan.minesweepers.common.game.Status
import com.cramsan.minesweepers.common.ui.theme.Padding
import com.cramsan.minesweepers.common.ui.theme.WINDOWS_GRAY

@Composable
internal fun GameBar(
    time: Int,
    minesRemaining: Int,
    status: Status,
    onRestartSelected: () -> Unit,
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(WINDOWS_GRAY)
            .border(Padding.SMALL, Color.Black)
            .padding(Padding.SMALL)
    ) {
        MinesRemainingDisplay(minesRemaining)
        Spacer(Modifier.weight(1f))

        ResetButton(status) {
            onRestartSelected()
        }

        Spacer(Modifier.weight(1f))
        TimePlayed(time)
    }
}
package com.myapplication.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.myapplication.common.game.CoverMode
import com.myapplication.common.game.Game
import com.myapplication.common.game.Tile

@Composable
internal fun App(
    time: Int,
    minesRemaining: Int,
    map: List<List<Tile>>,
    gameState: Game.GameState,
    onTileSelected: (column: Int, row: Int) -> Unit,
    onTileSelectedSecondary: (column: Int, row: Int) -> Unit,
    onRestartSelected: () -> Unit,
) {
    var isTilePressed by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier.background(Color(0xFFC6C6C6))
        ) {
            minesRemainingDisplay(minesRemaining)
            Spacer(Modifier.weight(1f))

            ResetButton(
                gameState.toFaceButtonState(),
                isTilePressed,
            ) { onRestartSelected() }

            Spacer(Modifier.weight(1f))
            timePlayed(time)
        }
        map.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { columnIndex, tile ->
                    TileButton(
                        tile,
                        columnIndex,
                        rowIndex,
                        onTileSelected,
                        onTileSelectedSecondary
                    ) {
                        isTilePressed = it
                    }
                }
            }
        }
    }
}

private fun Game.GameState.toFaceButtonState() = when (this) {
    Game.GameState.NORMAL -> FaceButtonState.NORMAL
    Game.GameState.WON -> FaceButtonState.WON
    Game.GameState.LOST -> FaceButtonState.DEAD
}

internal enum class FaceButtonState {
    NORMAL,
    DEAD,
    PRESSED,
    WON,
    WORRIED,
}

@Composable
internal fun ResetButton(
    faceButtonState: FaceButtonState,
    isTilePressed: Boolean,
    onRestartSelected: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val coalescedState = if (faceButtonState == FaceButtonState.DEAD || faceButtonState == FaceButtonState.WON) {
        faceButtonState
    } else if (isPressed) {
        FaceButtonState.PRESSED
    } else if (isTilePressed) {
        FaceButtonState.WORRIED
    } else {
        faceButtonState
    }

    Button(onRestartSelected) {
        val text = when(coalescedState) {
            FaceButtonState.NORMAL -> ":)"
            FaceButtonState.DEAD -> ":X"
            FaceButtonState.PRESSED -> ":)"
            FaceButtonState.WON -> ":D"
            FaceButtonState.WORRIED -> ":o"
        }
        Text(text)
    }
}

@Composable
internal fun minesRemainingDisplay(
    minesRemaining: Int,
) {
    Text(minesRemaining.toString())
}

@Composable
internal fun timePlayed(
    time: Int,
) {
    Text(time.toString())
}

@Composable
internal fun TileButton(
    tile: Tile,
    column: Int,
    row: Int,
    onTileSelected: (column: Int, row: Int) -> Unit,
    onTileSelectedSecondary: (column: Int, row: Int) -> Unit,
    onPressed: (isPressed: Boolean) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val content = when (tile.coverMode) {
        CoverMode.COVERED -> "O"
        CoverMode.FLAGGED -> "P"
        CoverMode.UNCOVERED -> when (tile) {
            is Tile.Adjacent -> tile.risk.toString()
            is Tile.Empty -> " "
            is Tile.Bomb -> when (tile.userSelection) {
                true -> "X"
                false -> "*"
            }
        }
    }

    onPressed(isPressed)

    TileButtonDrawable(
        content,
        interactionSource,
        { onTileSelected(column, row) },
        { onTileSelectedSecondary(column, row) },
    )
}

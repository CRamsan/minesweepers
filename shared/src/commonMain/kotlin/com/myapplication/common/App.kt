package com.myapplication.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap

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

            val interactionSource = remember { MutableInteractionSource() }
            FaceButton(
                gameState.toFaceButtonState(),
                isTilePressed,
                interactionSource,
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
@Composable
internal expect fun FaceButton(
    faceButtonState: FaceButtonState,
    isTilePressed: Boolean,
    mutableInteractionSource: MutableInteractionSource,
    onRestartSelected: () -> Unit
)
internal enum class FaceButtonState {
    NORMAL,
    DEAD,
    PRESSED,
    WON,
    WORRIED,
}

internal fun FaceButtonState.toImageBitmap(): ImageBitmap = when(this) {
    FaceButtonState.NORMAL -> Assets.buttonNormal
    FaceButtonState.DEAD -> Assets.buttonDead
    FaceButtonState.PRESSED -> Assets.buttonPressed
    FaceButtonState.WON -> Assets.buttonWon
    FaceButtonState.WORRIED -> Assets.buttonWorried
}

@Composable
internal fun minesRemainingDisplay(
    minesRemaining: Int,
) {
    lcdDisplay(3, minesRemaining)
}

@Composable
internal fun timePlayed(
    time: Int,
) {
    lcdDisplay(3, time)
}

@Composable
private fun lcdDisplay(
    digits: Int,
    value: Int,
) {
    Row {
        repeat(digits) {
            val digit = value.toString().padStart(digits)[it].digitToIntOrNull()
            val imageBitmap = when(digit) {
                0 -> Assets.lcdNumberZero
                1 -> Assets.lcdNumberOne
                2 -> Assets.lcdNumberTwo
                3 -> Assets.lcdNumberThree
                4 -> Assets.lcdNumberFour
                5 -> Assets.lcdNumberFive
                6 -> Assets.lcdNumberSix
                7 -> Assets.lcdNumberSeven
                8 -> Assets.lcdNumberEight
                9 -> Assets.lcdNumberNine
                else -> Assets.lcdNumberNone
            }
            Image(
                imageBitmap,
                contentDescription = digit.toString()
            )
        }
    }
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

    val imageBitmap = when (tile.coverMode) {
        CoverMode.COVERED -> Assets.tile
        CoverMode.FLAGGED -> Assets.tileFlagged
        CoverMode.UNCOVERED -> when (tile) {
            is Tile.Adjacent -> when (tile.risk) {
                1 -> Assets.pressedTileOne
                2 -> Assets.pressedTileTwo
                3 -> Assets.pressedTileThree
                4 -> Assets.pressedTileFour
                5 -> Assets.pressedTileFive
                6 -> Assets.pressedTileSix
                7 -> Assets.pressedTileSeven
                8 -> Assets.pressedTileEight
                else -> Assets.pressedTileEight
            }
            is Tile.Empty -> Assets.pressedTile
            is Tile.Bomb -> when (tile.userSelection) {
                true -> Assets.pressedTileBombRed
                false -> Assets.pressedTileBomb
            }
        }
    }

    onPressed(isPressed)

    TileButtonDrawable(
        imageBitmap,
        interactionSource,
        { onTileSelected(column, row) },
        { onTileSelectedSecondary(column, row) },
    )
}

@Composable
expect fun TileButtonDrawable(
    imageBitmap: ImageBitmap,
    mutableInteractionSource: MutableInteractionSource,
    onTileSelected: () -> Unit,
    onTileSelectedSecondary: () -> Unit,
)
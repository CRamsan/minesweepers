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
    assets: Assets,
    onTileSelected: (column: Int, row: Int) -> Unit,
    onTileSelectedSecondary: (column: Int, row: Int) -> Unit,
    onRestartSelected: () -> Unit,
) {
    var isTilePressed by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier.background(Color(0xFFC6C6C6))
        ) {
            minesRemainingDisplay(minesRemaining, assets)
            Spacer(Modifier.weight(1f))

            val interactionSource = remember { MutableInteractionSource() }
            FaceButton(
                gameState.toFaceButtonState(),
                isTilePressed,
                interactionSource,
                assets,
            ) { onRestartSelected() }

            Spacer(Modifier.weight(1f))
            timePlayed(time, assets)
        }
        map.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { columnIndex, tile ->
                    TileButton(
                        tile,
                        columnIndex,
                        rowIndex,
                        assets,
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
    assets: Assets,
    onRestartSelected: () -> Unit
)
internal enum class FaceButtonState {
    NORMAL,
    DEAD,
    PRESSED,
    WON,
    WORRIED,
}

internal fun FaceButtonState.toImageBitmap(assets: Assets): ImageBitmap = when(this) {
    FaceButtonState.NORMAL -> assets.buttonNormal
    FaceButtonState.DEAD -> assets.buttonDead
    FaceButtonState.PRESSED -> assets.buttonPressed
    FaceButtonState.WON -> assets.buttonWon
    FaceButtonState.WORRIED -> assets.buttonWorried
}

@Composable
internal fun minesRemainingDisplay(
    minesRemaining: Int,
    assets: Assets,
) {
    lcdDisplay(3, minesRemaining, assets)
}

@Composable
internal fun timePlayed(
    time: Int,
    assets: Assets,
) {
    lcdDisplay(3, time, assets)
}

@Composable
private fun lcdDisplay(
    digits: Int,
    value: Int,
    assets: Assets,
) {
    Row {
        repeat(digits) {
            val digit = value.toString().padStart(digits)[it].digitToIntOrNull()
            val imageBitmap = when(digit) {
                0 -> assets.lcdNumberZero
                1 -> assets.lcdNumberOne
                2 -> assets.lcdNumberTwo
                3 -> assets.lcdNumberThree
                4 -> assets.lcdNumberFour
                5 -> assets.lcdNumberFive
                6 -> assets.lcdNumberSix
                7 -> assets.lcdNumberSeven
                8 -> assets.lcdNumberEight
                9 -> assets.lcdNumberNine
                else -> assets.lcdNumberNone
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
    assets: Assets,
    onTileSelected: (column: Int, row: Int) -> Unit,
    onTileSelectedSecondary: (column: Int, row: Int) -> Unit,
    onPressed: (isPressed: Boolean) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val imageBitmap = when (tile.coverMode) {
        CoverMode.COVERED -> assets.tile
        CoverMode.FLAGGED -> assets.tileFlagged
        CoverMode.UNCOVERED -> when (tile) {
            is Tile.Adjacent -> when (tile.risk) {
                1 -> assets.pressedTileOne
                2 -> assets.pressedTileTwo
                3 -> assets.pressedTileThree
                4 -> assets.pressedTileFour
                5 -> assets.pressedTileFive
                6 -> assets.pressedTileSix
                7 -> assets.pressedTileSeven
                8 -> assets.pressedTileEight
                else -> assets.pressedTileEight
            }
            is Tile.Empty -> assets.pressedTile
            is Tile.Bomb -> when (tile.userSelection) {
                true -> assets.pressedTileBombRed
                false -> assets.pressedTileBomb
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
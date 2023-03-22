package com.cramsan.minesweepers.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.cramsan.minesweepers.common.game.TileCoverMode
import com.cramsan.minesweepers.common.game.Game
import com.cramsan.minesweepers.common.game.Tile
import org.jetbrains.compose.resources.ExperimentalResourceApi

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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .background(Color(0xFFC6C6C6))
                .border(3.dp, Color.Black)
                .padding(Padding.SMALL)
        ) {
            minesRemainingDisplay(minesRemaining)
            Spacer(Modifier.weight(1f))

            ResetButton(
                gameState.toFaceButtonState(),
            ) { onRestartSelected() }

            Spacer(Modifier.weight(1f))
            timePlayed(time)
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .border(3.dp, Color.Black)
            ) {

                map.forEachIndexed { rowIndex, row ->
                    Row {
                        row.forEachIndexed { columnIndex, tile ->
                            TileButton(
                                tile,
                                columnIndex,
                                rowIndex,
                                onTileSelected,
                                onTileSelectedSecondary
                            )
                        }
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
}

@Composable
internal fun ResetButton(
    faceButtonState: FaceButtonState,
    onRestartSelected: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val coalescedState = if (faceButtonState == FaceButtonState.DEAD || faceButtonState == FaceButtonState.WON) {
        faceButtonState
    } else if (isPressed) {
        FaceButtonState.PRESSED
    } else {
        faceButtonState
    }

    println(coalescedState)
    Image(
        coalescedState.toImageBitmap(),
        contentDescription = "",
        filterQuality = FilterQuality.None,
        modifier = Modifier
            .size(Dimensions.BUTTON_SIZE)
            .clickable { onRestartSelected() }
    )
}

@Composable
private fun FaceButtonState.toImageBitmap(): ImageBitmap = when(this) {
    FaceButtonState.NORMAL -> Assets.buttonNormal()
    FaceButtonState.DEAD -> Assets.buttonDead()
    FaceButtonState.PRESSED -> Assets.buttonPressed()
    FaceButtonState.WON -> Assets.buttonWon()
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
                0 -> Assets.lcdNumberZero()
                1 -> Assets.lcdNumberOne()
                2 -> Assets.lcdNumberTwo()
                3 -> Assets.lcdNumberThree()
                4 -> Assets.lcdNumberFour()
                5 -> Assets.lcdNumberFive()
                6 -> Assets.lcdNumberSix()
                7 -> Assets.lcdNumberSeven()
                8 -> Assets.lcdNumberEight()
                9 -> Assets.lcdNumberNine()
                else -> Assets.lcdNumberNone()
            }
            Image(
                imageBitmap,
                contentDescription = digit.toString(),
                filterQuality = FilterQuality.None,
                modifier = Modifier.size(Dimensions.LCD_WIDTH, Dimensions.LCD_HEIGHT),
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun TileButton(
    tile: Tile,
    column: Int,
    row: Int,
    onTileSelected: (column: Int, row: Int) -> Unit,
    onTileSelectedSecondary: (column: Int, row: Int) -> Unit,
) {
    val imageBitmap = when (tile.coverMode) {
        TileCoverMode.COVERED -> Assets.tile()
        TileCoverMode.FLAGGED -> Assets.tileFlagged()
        TileCoverMode.UNCOVERED -> when (tile) {
            is Tile.Adjacent -> when (tile.risk) {
                1 -> Assets.pressedTileOne()
                2 -> Assets.pressedTileTwo()
                3 -> Assets.pressedTileThree()
                4 -> Assets.pressedTileFour()
                5 -> Assets.pressedTileFive()
                6 -> Assets.pressedTileSix()
                7 -> Assets.pressedTileSeven()
                8 -> Assets.pressedTileEight()
                else -> Assets.pressedTileEight()
            }
            is Tile.Empty -> Assets.pressedTile()
            is Tile.Bomb -> when (tile.userSelection) {
                true -> Assets.pressedTileBombRed()
                false -> Assets.pressedTileBomb()
            }
        }
    }

    TileButtonDrawable(
        imageBitmap,
        { onTileSelected(column, row) },
    ) { onTileSelectedSecondary(column, row) }
}

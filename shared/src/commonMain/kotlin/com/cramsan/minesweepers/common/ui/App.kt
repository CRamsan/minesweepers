package com.cramsan.minesweepers.common.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.cramsan.minesweepers.common.game.Game
import com.cramsan.minesweepers.common.game.Tile
import com.cramsan.minesweepers.common.game.TileCoverMode
import org.jetbrains.compose.resources.ExperimentalResourceApi
import kotlin.math.min

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
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }
        var boxSize by remember { mutableStateOf(IntSize.Zero) }
        var mapSize by remember { mutableStateOf(IntSize.Zero) }
        var mapPos by remember { mutableStateOf(Offset.Zero) }

        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(Color(0xFFC6C6C6))
                .border(3.dp, Color.Black)
                .padding(Padding.SMALL)
        ) {
            minesRemainingDisplay(minesRemaining)
            Spacer(Modifier.weight(1f))

            ResetButton(
                gameState.toFaceButtonState(),
            ) {
                offsetX = 0f
                offsetY = 0f
                onRestartSelected()
            }

            Spacer(Modifier.weight(1f))
            timePlayed(time)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(3.dp, Color.Black)
                .padding(3.dp)
                .clipToBounds()
                .onGloballyPositioned { coordinates ->
                    boxSize = coordinates.size
                },
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize(unbounded = true)
                    .offset {
                        IntOffset(
                            x = (offsetX).toInt(),
                            y = (offsetY).toInt(),
                        )
                    }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change: PointerInputChange, dragAmount: Offset ->
                                change.consume()
                                if (dragAmount.x > 0) {
                                    if (mapPos.x < 0) {
                                        offsetX += dragAmount.x
                                    }
                                } else {
                                    if ((mapPos.x + mapSize.width) > (boxSize.width)) {
                                        offsetX += dragAmount.x
                                    }
                                }

                                if (dragAmount.y > 0) {
                                    if (mapPos.y <  0) {
                                        offsetY += dragAmount.y
                                    }
                                } else {
                                    if ((mapPos.y + mapSize.height) > (boxSize.height)) {
                                        offsetY += dragAmount.y
                                    }
                                }
                            }
                        )
                    }
                    .onGloballyPositioned { coordinates ->
                        mapSize = coordinates.size
                        mapPos = coordinates.positionInParent()
                    }
            ) {
                map.forEachIndexed { rowIndex, row ->
                    Row(
                        modifier = Modifier.wrapContentSize(unbounded = true)
                    ) {
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

            val density = LocalDensity.current.density
            if (mapPos.y < -(Dimensions.TILE_SIZE.value * density)) {
                Row (modifier = Modifier.align(Alignment.TopCenter)) {
                    ArrowDownRow()
                }
            }
            if (mapPos.y + mapSize.height > (boxSize.height + (Dimensions.TILE_SIZE.value * density))) {
                Row (modifier = Modifier.align(Alignment.BottomCenter)) {
                    ArrowUpRow()
                }
            }

            if (mapPos.x < -(Dimensions.TILE_SIZE.value * density)) {
                Column (modifier = Modifier.align(Alignment.CenterStart)) {
                    ArrowRightColumn()
                }
            }
            if (mapPos.x + mapSize.width > (boxSize.width + (Dimensions.TILE_SIZE.value * density))) {
                Column (modifier = Modifier.align(Alignment.CenterEnd)) {
                    ArrowLeftColumn()
                }
            }
        }
    }
}

@Composable
private fun ArrowDownRow() {
    repeat(3) {
        Image(
            Assets.arrowDown(),
            contentDescription = "",
            filterQuality = FilterQuality.None,
            modifier = Modifier.size(
                Dimensions.ARROW_H_WIDTH,
                Dimensions.ARROW_H_HEIGHT
            )
                .padding(3.dp)
        )
    }
}

@Composable
private fun ArrowUpRow() {
    repeat(3) {
        Image(
            Assets.arrowUp(),
            contentDescription = "",
            filterQuality = FilterQuality.None,
            modifier = Modifier.size(
                Dimensions.ARROW_H_WIDTH,
                Dimensions.ARROW_H_HEIGHT
            )
                .padding(3.dp)
        )
    }
}

@Composable
private fun ArrowLeftColumn() {
    repeat(3) {
        Image(
            Assets.arrowLeft(),
            contentDescription = "",
            filterQuality = FilterQuality.None,
            modifier = Modifier.size(
                Dimensions.ARROW_V_WIDTH,
                Dimensions.ARROW_V_HEIGHT
            )
                .padding(3.dp)
        )
    }
}

@Composable
private fun ArrowRightColumn() {
    repeat(3) {
        Image(
            Assets.arrowRight(),
            contentDescription = "",
            filterQuality = FilterQuality.None,
            modifier = Modifier.size(
                Dimensions.ARROW_V_WIDTH,
                Dimensions.ARROW_V_HEIGHT
            )
                .padding(3.dp)
        )
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

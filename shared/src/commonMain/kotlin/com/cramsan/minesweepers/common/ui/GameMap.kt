package com.cramsan.minesweepers.common.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.cramsan.minesweepers.common.game.Tile
import com.cramsan.minesweepers.common.ui.theme.Dimensions
import com.cramsan.minesweepers.common.ui.theme.Padding


@Composable
@Suppress("LongMethod", "MaxLineLength")
internal fun GameMap(
    map: List<List<Tile>>,
    onTileSelected: (column: Int, row: Int) -> Unit,
    onTileSelectedSecondary: (column: Int, row: Int) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }
        var boxSize by remember { mutableStateOf(IntSize.Zero) }
        var mapSize by remember { mutableStateOf(IntSize.Zero) }
        var mapPos by remember { mutableStateOf(Offset.Zero) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(Padding.SMALL, Color.Black)
                .padding(Padding.SMALL)
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
                                    if (mapPos.x < (Dimensions.TILE_SIZE.value*density)) {
                                        offsetX += dragAmount.x
                                    }
                                } else {
                                    if ((mapPos.x + mapSize.width) > (boxSize.width - (Dimensions.TILE_SIZE.value*density))) {
                                        offsetX += dragAmount.x
                                    }
                                }

                                if (dragAmount.y > 0) {
                                    if (mapPos.y <  (Dimensions.TILE_SIZE.value*density)) {
                                        offsetY += dragAmount.y
                                    }
                                } else {
                                    if ((mapPos.y + mapSize.height) > (boxSize.height - (Dimensions.TILE_SIZE.value*density))) {
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
                ArrowUpRow(Modifier.align(Alignment.TopCenter).wrapContentSize(unbounded = true))
            }
            if (mapPos.y + mapSize.height > (boxSize.height + (Dimensions.TILE_SIZE.value * density))) {
                ArrowDownRow(Modifier.align(Alignment.BottomCenter).wrapContentSize(unbounded = true))
            }

            if (mapPos.x < -(Dimensions.TILE_SIZE.value * density)) {
                ArrowLeftColumn(Modifier.align(Alignment.CenterStart).wrapContentSize(unbounded = true))
            }
            if (mapPos.x + mapSize.width > (boxSize.width + (Dimensions.TILE_SIZE.value * density))) {
                ArrowRightColumn(Modifier.align(Alignment.CenterEnd).wrapContentSize(unbounded = true))
            }
        }
    }
}

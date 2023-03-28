package com.cramsan.minesweepers.common.ui

import androidx.compose.runtime.Composable
import com.cramsan.minesweepers.common.game.Tile
import com.cramsan.minesweepers.common.game.TileCoverMode

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

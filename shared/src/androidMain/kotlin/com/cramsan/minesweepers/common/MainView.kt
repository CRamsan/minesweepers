package com.cramsan.minesweepers.common

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cramsan.minesweepers.common.game.Status
import com.cramsan.minesweepers.common.game.Tile
import com.cramsan.minesweepers.common.game.TileCoverMode
import com.cramsan.minesweepers.common.ui.GameBar
import com.cramsan.minesweepers.common.ui.GameMap

@Composable
fun MainView(
    time: Int,
    minesRemaining: Int,
    map: List<List<Tile>>,
    status: Status,
    onTileSelected: (column: Int, row: Int) -> Unit,
    onTileSelectedSecondary: (column: Int, row: Int) -> Unit,
    onRestartSelected: () -> Unit,
) {
    Column {
        GameBar(time, minesRemaining, status, onRestartSelected)
        GameMap(map, onTileSelected, onTileSelectedSecondary)
    }
}

@Preview
@Composable
fun AppPreview() {
    MainView(20, 5, listOf(
        listOf(
            Tile.Empty(TileCoverMode.COVERED),
            Tile.Empty(TileCoverMode.UNCOVERED),
            Tile.Empty(TileCoverMode.COVERED)
        ),
        listOf(
            Tile.Bomb(TileCoverMode.COVERED),
            Tile.Bomb(TileCoverMode.FLAGGED),
            Tile.Bomb(TileCoverMode.COVERED)
        ),
        listOf(
            Tile.Adjacent(1, TileCoverMode.UNCOVERED),
            Tile.Adjacent(2, TileCoverMode.UNCOVERED),
            Tile.Adjacent(3, TileCoverMode.COVERED)
        ),
    ), Status.WON, { _, _ -> }, { _, _ -> }, {})
}

package com.cramsan.minesweepers.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cramsan.minesweepers.common.game.TileCoverMode
import com.cramsan.minesweepers.common.game.Game
import com.cramsan.minesweepers.common.game.Tile
import com.cramsan.minesweepers.common.ui.App

@Composable fun MainView(
    time: Int,
    minesRemaining: Int,
    map: List<List<Tile>>,
    gameState: Game.GameState,
    onTileSelected: (column: Int, row: Int) -> Unit,
    onTileSelectedSecondary: (column: Int, row: Int) -> Unit,
    onRestartSelected: () -> Unit,
) {
    App(time, minesRemaining, map, gameState, onTileSelected, onTileSelectedSecondary, onRestartSelected)
}

@Preview
@Composable
fun AppPreview() {
    App(20, 5, listOf(
        listOf(Tile.Empty(TileCoverMode.COVERED), Tile.Empty(TileCoverMode.UNCOVERED), Tile.Empty(TileCoverMode.COVERED)),
        listOf(Tile.Bomb(TileCoverMode.COVERED), Tile.Bomb(TileCoverMode.FLAGGED), Tile.Bomb(TileCoverMode.COVERED)),
        listOf(Tile.Adjacent(1, TileCoverMode.UNCOVERED), Tile.Adjacent(2, TileCoverMode.UNCOVERED), Tile.Adjacent(3, TileCoverMode.COVERED)),
    ), Game.GameState.WON, { _, _ -> }, { _, _ -> }, {})
}
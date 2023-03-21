package com.cramsan.minesweepers.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cramsan.minesweepers.common.game.CoverMode
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
        listOf(Tile.Empty(CoverMode.COVERED), Tile.Empty(CoverMode.UNCOVERED), Tile.Empty(CoverMode.COVERED)),
        listOf(Tile.Bomb(CoverMode.COVERED), Tile.Bomb(CoverMode.FLAGGED), Tile.Bomb(CoverMode.COVERED)),
        listOf(Tile.Adjacent(1, CoverMode.UNCOVERED), Tile.Adjacent(2, CoverMode.UNCOVERED), Tile.Adjacent(3, CoverMode.COVERED)),
    ), Game.GameState.WON, { _, _ -> }, { _, _ -> }, {})
}
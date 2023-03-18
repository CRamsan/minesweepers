package com.myapplication.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.myapplication.common.App
import com.myapplication.common.CoverMode
import com.myapplication.common.Game
import com.myapplication.common.Tile

@Composable fun MainView(
    time: Int,
    minesRemaining: Int,
    map: List<List<Tile>>,
    gameState: Game.GameState,
    assets: Assets,
    onTileSelected: (column: Int, row: Int) -> Unit,
    onTileSelectedSecondary: (column: Int, row: Int) -> Unit,
    onRestartSelected: () -> Unit,
) {
    App(time, minesRemaining, map, gameState, assets, onTileSelected, onTileSelectedSecondary, onRestartSelected)
}
@Preview
@Composable
fun AppPreview() {
    App(20, 5, listOf(
        listOf(Tile.Empty(CoverMode.COVERED), Tile.Empty(CoverMode.UNCOVERED), Tile.Empty(CoverMode.COVERED)),
        listOf(Tile.Bomb(CoverMode.COVERED), Tile.Bomb(CoverMode.FLAGGED), Tile.Bomb(CoverMode.COVERED)),
        listOf(Tile.Adjacent(1, CoverMode.UNCOVERED), Tile.Adjacent(2, CoverMode.UNCOVERED), Tile.Adjacent(3, CoverMode.COVERED)),
    ), Game.GameState.WON, Assets(), { _, _ -> }, { _, _ -> }, {})
}
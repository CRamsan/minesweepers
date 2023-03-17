package com.myapplication.common

import androidx.compose.runtime.Composable

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
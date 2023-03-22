package com.cramsan.minesweepers.common

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.ComposeUIViewController
import com.cramsan.minesweepers.common.game.Game
import com.cramsan.minesweepers.common.ui.App

fun MainViewController(game: Game) = ComposeUIViewController {

    val map by game.gameStateHolder.map.collectAsState()
    val time by game.gameStateHolder.time.collectAsState()
    val minesRemaining by game.gameStateHolder.minesRemaining.collectAsState()
    val gameState by game.gameStateHolder.gameState.collectAsState()
    val isGameReady by game.isGameReady.collectAsState()

    if (isGameReady) {
        App(
            time,
            minesRemaining,
            map,
            gameState,
            { column, row -> game.selectPosition(column, row) },
            { column, row -> game.toggleTileAtPosition(column, row) },
            { game.setParameters() },
        )
    }
}
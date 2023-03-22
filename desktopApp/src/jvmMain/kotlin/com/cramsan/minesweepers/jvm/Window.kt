package com.cramsan.minesweepers.jvm

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.cramsan.minesweepers.common.MainView
import com.cramsan.minesweepers.common.game.Game
import com.cramsan.minesweepers.common.ui.Assets

fun main() = application {
    val game = Game()
    game.loadAssets()
    game.setParameters()

    val map by game.gameStateHolder.map.collectAsState()
    val time by game.gameStateHolder.time.collectAsState()
    val minesRemaining by game.gameStateHolder.minesRemaining.collectAsState()
    val gameState by game.gameStateHolder.gameState.collectAsState()
    val isGameReady by game.isGameReady.collectAsState()

    if (isGameReady) {
        Window(onCloseRequest = ::exitApplication) {
            MainView(
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
}
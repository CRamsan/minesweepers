package com.cramsan.minesweepers.jvm

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.cramsan.minesweepers.common.MainView
import com.cramsan.minesweepers.common.game.Game

fun main() = application {
    val game = Game()
    game.loadAssetsAsync()
    game.configure()

    val map by game.gameStateHolder.map.collectAsState()
    val time by game.gameStateHolder.time.collectAsState()
    val minesRemaining by game.gameStateHolder.minesRemaining.collectAsState()
    val gameState by game.gameStateHolder.status.collectAsState()
    val isGameReady by game.initialized.collectAsState()

    if (isGameReady) {
        Window(
            title = "Minesweepers",
            onCloseRequest = ::exitApplication,
        ) {
            MainView(
                time,
                minesRemaining,
                map,
                gameState,
                { column, row -> game.primaryAction(column, row) },
                { column, row -> game.secondaryAction(column, row) },
                { game.configure() },
            )
        }
    }
}

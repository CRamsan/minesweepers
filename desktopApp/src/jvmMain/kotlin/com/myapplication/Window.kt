package com.myapplication

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.myapplication.common.game.Game
import com.myapplication.common.MainView

fun main() = application {
    val game = Game()
    game.setParameters()

    val map by game.gameStateHolder.map.collectAsState()
    val time by game.gameStateHolder.time.collectAsState()
    val minesRemaining by game.gameStateHolder.minesRemaining.collectAsState()
    val gameState by game.gameStateHolder.gameState.collectAsState()

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
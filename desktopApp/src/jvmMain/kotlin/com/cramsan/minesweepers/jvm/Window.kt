package com.cramsan.minesweepers.jvm

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.cramsan.minesweepers.common.MainView
import com.cramsan.minesweepers.common.game.Game

fun main() = application {
    val game = Game()
    game.configure()

    Window(
        title = "Minesweepers",
        onCloseRequest = ::exitApplication,
    ) {
        val map by game.gameStateHolder.map.collectAsState()
        val time by game.gameStateHolder.time.collectAsState()
        val minesRemaining by game.gameStateHolder.minesRemaining.collectAsState()
        val gameState by game.gameStateHolder.status.collectAsState()

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

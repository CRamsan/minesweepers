package com.cramsan.minesweepers.jvm

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
        Window(
            undecorated = true,
            onCloseRequest = ::exitApplication,
        ) {
            WindowDraggableArea {
                Box {
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
    }
}
package com.cramsan.minesweepers.common

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.ComposeUIViewController
import com.cramsan.minesweepers.common.game.Game
import com.cramsan.minesweepers.common.ui.GameBar
import com.cramsan.minesweepers.common.ui.GameMap

/**
 * ViewController to expose the screen for the iOS platform.
 */
@Suppress("FunctionNaming")
fun MainViewController(game: Game) = ComposeUIViewController {

    val map by game.gameStateHolder.map.collectAsState()
    val time by game.gameStateHolder.time.collectAsState()
    val minesRemaining by game.gameStateHolder.minesRemaining.collectAsState()
    val status by game.gameStateHolder.status.collectAsState()

    Column {
        GameBar(time, minesRemaining, status) { game.configure() }
        GameMap(
            map,
            { column, row -> game.primaryAction(column, row) },
            { column, row -> game.secondaryAction(column, row) },
        )
    }
}

package com.myapplication.common

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.ComposeUIViewController
import com.myapplication.common.game.Game
import com.myapplication.common.ui.App

fun MainViewController(game: Game) = ComposeUIViewController {

    val map by game.gameStateHolder.map.collectAsState()
    val time by game.gameStateHolder.time.collectAsState()
    val minesRemaining by game.gameStateHolder.minesRemaining.collectAsState()
    val gameState by game.gameStateHolder.gameState.collectAsState()

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
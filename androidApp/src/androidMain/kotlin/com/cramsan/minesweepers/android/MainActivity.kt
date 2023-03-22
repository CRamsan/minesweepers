package com.cramsan.minesweepers.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cramsan.minesweepers.common.MainView

import com.cramsan.minesweepers.common.game.Game

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game = Game()
        game.setParameters()
        game.loadAssets()

        setContent {
            val map by game.gameStateHolder.map.collectAsState()
            val time by game.gameStateHolder.time.collectAsState()
            val minesRemaining by game.gameStateHolder.minesRemaining.collectAsState()
            val gameState by game.gameStateHolder.gameState.collectAsState()
            val isGameReady by game.isGameReady.collectAsState()

            if (isGameReady) {
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
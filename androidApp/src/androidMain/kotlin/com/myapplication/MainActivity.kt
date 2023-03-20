package com.myapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue

import com.myapplication.common.game.Game
import com.myapplication.common.MainView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game = Game()
        game.setParameters()

        setContent {
            val map by game.gameStateHolder.map.collectAsState()
            val time by game.gameStateHolder.time.collectAsState()
            val minesRemaining by game.gameStateHolder.minesRemaining.collectAsState()
            val gameState by game.gameStateHolder.gameState.collectAsState()

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
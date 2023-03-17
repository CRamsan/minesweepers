package com.myapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue

import com.myapplication.common.Game
import com.myapplication.common.MainView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game = Game()
        game.setParameters()

        setContent {
            val map by remember { game.gameStateHolder.map }
            val time by remember { game.gameStateHolder.time }
            val minesRemaining by remember { game.gameStateHolder.minesRemaining }
            val gameState by remember { game.gameStateHolder.gameState }

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
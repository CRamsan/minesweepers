package com.myapplication

import Game
import MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game = Game(Random)
        game.setParameters(10, 10, 10)

        setContent {
            val map by remember { game.mapState }
            MainView(map) { column, row ->
                game.selectPosition(column, row)
            }
        }
    }
}
package com.myapplication.common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State

interface GameStateHolder {
    val time: State<Int>
    val minesRemaining: State<Int>
    val map: MutableState<List<List<Tile>>>
    val gameState: MutableState<Game.GameState>
}

class MutableGameStateHolder(
    override val time: MutableState<Int>,
    override val minesRemaining: MutableState<Int>,
    override val map: MutableState<List<List<Tile>>>,
    override val gameState: MutableState<Game.GameState>,
) : GameStateHolder
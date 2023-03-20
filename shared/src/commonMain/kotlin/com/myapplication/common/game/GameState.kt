package com.myapplication.common.game

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface GameStateHolder {
    val time: StateFlow<Int>
    val minesRemaining: StateFlow<Int>
    val map: StateFlow<List<List<Tile>>>
    val gameState: StateFlow<Game.GameState>
}

class MutableGameStateHolder(
    override val time: MutableStateFlow<Int>,
    override val minesRemaining: MutableStateFlow<Int>,
    override val map: MutableStateFlow<List<List<Tile>>>,
    override val gameState: MutableStateFlow<Game.GameState>,
) : GameStateHolder
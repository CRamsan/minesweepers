package com.cramsan.minesweepers.common.game

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface GameStateHolder {
    val time: StateFlow<Int>
    val minesRemaining: StateFlow<Int>
    val map: StateFlow<List<List<Tile>>>
    val status: StateFlow<Status>
}

class MutableGameStateHolder(
    override val time: MutableStateFlow<Int>,
    override val minesRemaining: MutableStateFlow<Int>,
    override val map: MutableStateFlow<List<List<Tile>>>,
    override val status: MutableStateFlow<Status>,
) : GameStateHolder

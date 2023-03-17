package com.myapplication.common

sealed class Tile(
    val tileType: TileType,
    open val coverMode: CoverMode,
) {
    data class Bomb(
        override val coverMode: CoverMode,
        val userSelection: Boolean = false,
    ) : Tile(TileType.BOMB, coverMode)

    data class Empty(
        override val coverMode: CoverMode,
    ) : Tile(TileType.EMPTY, coverMode)

    data class Adjacent(
        val risk: Int,
        override val coverMode: CoverMode,
    ): Tile(TileType.EMPTY, coverMode)
}
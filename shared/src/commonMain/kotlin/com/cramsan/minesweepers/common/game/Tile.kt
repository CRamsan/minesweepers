package com.cramsan.minesweepers.common.game

sealed class Tile(
    val tileType: TileType,
    open val coverMode: TileCoverMode,
) {
    data class Bomb(
        override val coverMode: TileCoverMode,
        val userSelection: Boolean = false,
    ) : Tile(TileType.BOMB, coverMode)

    data class Empty(
        override val coverMode: TileCoverMode,
    ) : Tile(TileType.EMPTY, coverMode)

    data class Adjacent(
        val risk: Int,
        override val coverMode: TileCoverMode,
    ): Tile(TileType.EMPTY, coverMode)
}
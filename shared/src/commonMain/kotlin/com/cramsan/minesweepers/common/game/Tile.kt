package com.cramsan.minesweepers.common.game

sealed class Tile(
    open val coverMode: TileCoverMode,
) {
    data class Bomb(
        override val coverMode: TileCoverMode,
        val userSelection: Boolean = false,
    ) : Tile(coverMode)

    data class Empty(
        override val coverMode: TileCoverMode,
    ) : Tile(coverMode)

    data class Adjacent(
        val risk: Int,
        override val coverMode: TileCoverMode,
    ): Tile(coverMode)
}

package com.cramsan.minesweepers.common.ui

import androidx.compose.runtime.Composable
import com.cramsan.minesweepers.common.game.Tile
import com.cramsan.minesweepers.common.game.TileCoverMode
import shared.Res
import shared.tile
import shared.tile_flagged
import shared.tile_pressed
import shared.tile_pressed_bomb
import shared.tile_pressed_bomb_red
import shared.tile_pressed_eight
import shared.tile_pressed_five
import shared.tile_pressed_four
import shared.tile_pressed_one
import shared.tile_pressed_seven
import shared.tile_pressed_six
import shared.tile_pressed_three
import shared.tile_pressed_two
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.imageResource

/**
 *
 */
@OptIn(ExperimentalResourceApi::class)
@Suppress("CyclomaticComplexMethod")
@Composable
internal fun TileButton(
    tile: Tile,
    column: Int,
    row: Int,
    onTileSelected: (column: Int, row: Int) -> Unit,
    onTileSelectedSecondary: (column: Int, row: Int) -> Unit,
) {
    val imageBitmap = when (tile.coverMode) {
        TileCoverMode.COVERED -> Res.drawable.tile
        TileCoverMode.FLAGGED -> Res.drawable.tile_flagged
        TileCoverMode.UNCOVERED -> when (tile) {
            is Tile.Adjacent -> when (tile.risk) {
                1 -> Res.drawable.tile_pressed_one
                2 -> Res.drawable.tile_pressed_two
                3 -> Res.drawable.tile_pressed_three
                4 -> Res.drawable.tile_pressed_four
                5 -> Res.drawable.tile_pressed_five
                6 -> Res.drawable.tile_pressed_six
                7 -> Res.drawable.tile_pressed_seven
                8 -> Res.drawable.tile_pressed_eight
                else -> Res.drawable.tile_pressed_eight
            }
            is Tile.Empty -> Res.drawable.tile_pressed
            is Tile.Bomb -> when (tile.userSelection) {
                true -> Res.drawable.tile_pressed_bomb_red
                false -> Res.drawable.tile_pressed_bomb
            }
        }
    }

    TileButtonDrawable(
        imageResource(imageBitmap),
        { onTileSelected(column, row) },
    ) { onTileSelectedSecondary(column, row) }
}

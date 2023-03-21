@file:OptIn(ExperimentalResourceApi::class)
package com.cramsan.minesweepers.common.ui

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.orEmpty
import org.jetbrains.compose.resources.rememberImageBitmap
import org.jetbrains.compose.resources.resource

object Assets {

    @Composable
    internal fun lcdNumberDash() = resource("dash.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun lcdNumberNone() = resource("none.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun lcdNumberOne() = resource("one.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun lcdNumberTwo() = resource("two.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun lcdNumberThree() = resource("three.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun lcdNumberFour() = resource("four.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun lcdNumberFive() = resource("five.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun lcdNumberSix() = resource("six.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun lcdNumberSeven() = resource("seven.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun lcdNumberEight() = resource("eight.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun lcdNumberNine() = resource("nine.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun lcdNumberZero() = resource("zero.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun tile() = resource("tile.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun tileFlagged() = resource("tile_flagged.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun pressedTile() = resource("tile_pressed.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun pressedTileBomb() = resource("tile_pressed_bomb.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun pressedTileBombRed() = resource("tile_pressed_bomb_red.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun pressedTileOne() = resource("tile_pressed_one.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun pressedTileTwo() = resource("tile_pressed_two.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun pressedTileThree() = resource("tile_pressed_three.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun pressedTileFour() = resource("tile_pressed_four.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun pressedTileFive() = resource("tile_pressed_five.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun pressedTileSix() = resource("tile_pressed_six.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun pressedTileSeven() = resource("tile_pressed_seven.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun pressedTileEight() = resource("tile_pressed_eight.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun buttonDead() = resource("button_dead.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun buttonNormal() = resource("button_normal.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun buttonPressed() = resource("button_pressed.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun buttonWon() = resource("button_won.png").rememberImageBitmap().orEmpty()

    @Composable
    internal fun buttonWorried() = resource("button_worried.png").rememberImageBitmap().orEmpty()
}
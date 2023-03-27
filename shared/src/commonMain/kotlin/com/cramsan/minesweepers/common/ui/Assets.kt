@file:OptIn(ExperimentalResourceApi::class)
package com.cramsan.minesweepers.common.ui

import androidx.compose.ui.graphics.ImageBitmap
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

@Suppress("TooManyFunctions")
object Assets {
    private lateinit var _lcdNumberDash: ImageBitmap
    private lateinit var _lcdNumberNone: ImageBitmap
    private lateinit var _lcdNumberOne: ImageBitmap
    private lateinit var _lcdNumberTwo: ImageBitmap
    private lateinit var _lcdNumberThree: ImageBitmap
    private lateinit var _lcdNumberFour: ImageBitmap
    private lateinit var _lcdNumberFive: ImageBitmap
    private lateinit var _lcdNumberSix: ImageBitmap
    private lateinit var _lcdNumberSeven: ImageBitmap
    private lateinit var _lcdNumberEight: ImageBitmap
    private lateinit var _lcdNumberNine: ImageBitmap
    private lateinit var _lcdNumberZero: ImageBitmap
    private lateinit var _tile: ImageBitmap
    private lateinit var _tileFlagged: ImageBitmap
    private lateinit var _pressedTile: ImageBitmap
    private lateinit var _pressedTileBomb: ImageBitmap
    private lateinit var _pressedTileBombRed: ImageBitmap
    private lateinit var _pressedTileOne: ImageBitmap
    private lateinit var _pressedTileTwo: ImageBitmap
    private lateinit var _pressedTileThree: ImageBitmap
    private lateinit var _pressedTileFour: ImageBitmap
    private lateinit var _pressedTileFive: ImageBitmap
    private lateinit var _pressedTileSix: ImageBitmap
    private lateinit var _pressedTileSeven: ImageBitmap
    private lateinit var _pressedTileEight: ImageBitmap
    private lateinit var _buttonDead: ImageBitmap
    private lateinit var _buttonNormal: ImageBitmap
    private lateinit var _buttonPressed: ImageBitmap
    private lateinit var _buttonWon: ImageBitmap
    private lateinit var _buttonWorried: ImageBitmap
    private lateinit var _arrowUp: ImageBitmap
    private lateinit var _arrowDown: ImageBitmap
    private lateinit var _arrowLeft: ImageBitmap
    private lateinit var _arrowRight: ImageBitmap

    suspend fun loadAssets() {
        _lcdNumberDash = resource("dash.png").readBytes().toImageBitmap()
        _lcdNumberNone = resource("none.png").readBytes().toImageBitmap()
        _lcdNumberOne = resource("one.png").readBytes().toImageBitmap()
        _lcdNumberTwo = resource("two.png").readBytes().toImageBitmap()
        _lcdNumberThree = resource("three.png").readBytes().toImageBitmap()
        _lcdNumberFour = resource("four.png").readBytes().toImageBitmap()
        _lcdNumberFive = resource("five.png").readBytes().toImageBitmap()
        _lcdNumberSix = resource("six.png").readBytes().toImageBitmap()
        _lcdNumberSeven = resource("seven.png").readBytes().toImageBitmap()
        _lcdNumberEight = resource("eight.png").readBytes().toImageBitmap()
        _lcdNumberNine = resource("nine.png").readBytes().toImageBitmap()
        _lcdNumberZero = resource("zero.png").readBytes().toImageBitmap()
        _tile = resource("tile.png").readBytes().toImageBitmap()
        _tileFlagged = resource("tile_flagged.png").readBytes().toImageBitmap()
        _pressedTile = resource("tile_pressed.png").readBytes().toImageBitmap()
        _pressedTileBomb = resource("tile_pressed_bomb.png").readBytes().toImageBitmap()
        _pressedTileBombRed = resource("tile_pressed_bomb_exploded.png").readBytes().toImageBitmap()
        _pressedTileOne = resource("tile_pressed_one.png").readBytes().toImageBitmap()
        _pressedTileTwo = resource("tile_pressed_two.png").readBytes().toImageBitmap()
        _pressedTileThree = resource("tile_pressed_three.png").readBytes().toImageBitmap()
        _pressedTileFour = resource("tile_pressed_four.png").readBytes().toImageBitmap()
        _pressedTileFive = resource("tile_pressed_five.png").readBytes().toImageBitmap()
        _pressedTileSix = resource("tile_pressed_six.png").readBytes().toImageBitmap()
        _pressedTileSeven = resource("tile_pressed_seven.png").readBytes().toImageBitmap()
        _pressedTileEight = resource("tile_pressed_eight.png").readBytes().toImageBitmap()
        _buttonDead = resource("button_dead.png").readBytes().toImageBitmap()
        _buttonNormal = resource("button_normal.png").readBytes().toImageBitmap()
        _buttonPressed = resource("button_pressed.png").readBytes().toImageBitmap()
        _buttonWon = resource("button_won.png").readBytes().toImageBitmap()
        _buttonWorried = resource("button_worried.png").readBytes().toImageBitmap()
        _arrowUp = resource("arrow_up.png").readBytes().toImageBitmap()
        _arrowDown = resource("arrow_down.png").readBytes().toImageBitmap()
        _arrowLeft = resource("arrow_left.png").readBytes().toImageBitmap()
        _arrowRight = resource("arrow_right.png").readBytes().toImageBitmap()
    }

    internal fun lcdNumberDash() = _lcdNumberDash
    internal fun lcdNumberNone() = _lcdNumberNone
    internal fun lcdNumberOne() = _lcdNumberOne
    internal fun lcdNumberTwo() = _lcdNumberTwo
    internal fun lcdNumberThree() = _lcdNumberThree
    internal fun lcdNumberFour() = _lcdNumberFour
    internal fun lcdNumberFive() = _lcdNumberFive
    internal fun lcdNumberSix() = _lcdNumberSix
    internal fun lcdNumberSeven() = _lcdNumberSeven
    internal fun lcdNumberEight() = _lcdNumberEight
    internal fun lcdNumberNine() = _lcdNumberNine
    internal fun lcdNumberZero() = _lcdNumberZero
    internal fun tile() = _tile
    internal fun tileFlagged() = _tileFlagged
    internal fun pressedTile() = _pressedTile
    internal fun pressedTileBomb() = _pressedTileBomb
    internal fun pressedTileBombRed() = _pressedTileBombRed
    internal fun pressedTileOne() = _pressedTileOne
    internal fun pressedTileTwo() = _pressedTileTwo
    internal fun pressedTileThree() = _pressedTileThree
    internal fun pressedTileFour() = _pressedTileFour
    internal fun pressedTileFive() = _pressedTileFive
    internal fun pressedTileSix() = _pressedTileSix
    internal fun pressedTileSeven() = _pressedTileSeven
    internal fun pressedTileEight() = _pressedTileEight
    internal fun buttonDead() = _buttonDead
    internal fun buttonNormal() = _buttonNormal
    internal fun buttonPressed() = _buttonPressed
    internal fun buttonWon() = _buttonWon
    internal fun buttonWorried() = _buttonWorried
    internal fun arrowUp() = _arrowUp
    internal fun arrowDown() = _arrowDown
    internal fun arrowLeft() = _arrowLeft
    internal fun arrowRight() = _arrowRight


}

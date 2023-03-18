package com.myapplication.common

import android.content.res.AssetManager
import android.content.res.AssetManager.AssetInputStream
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.myapplication.common.R

actual class Assets(
    private val resources: Resources
) {
    actual val lcdNumberDash by lazy { BitmapFactory.decodeStream(resources.assets.open("dash.png")).asImageBitmap() }

    actual val lcdNumberNone by lazy { BitmapFactory.decodeStream(resources.assets.open("none.png")).asImageBitmap() }

    actual val lcdNumberOne by lazy { BitmapFactory.decodeStream(resources.assets.open("one.png")).asImageBitmap() }

    actual val lcdNumberTwo by lazy { BitmapFactory.decodeStream(resources.assets.open("two.png")).asImageBitmap() }

    actual val lcdNumberThree by lazy { BitmapFactory.decodeStream(resources.assets.open("three.png")).asImageBitmap() }

    actual val lcdNumberFour by lazy { BitmapFactory.decodeStream(resources.assets.open("four.png")).asImageBitmap() }

    actual val lcdNumberFive by lazy { BitmapFactory.decodeStream(resources.assets.open("five.png")).asImageBitmap() }

    actual val lcdNumberSix by lazy { BitmapFactory.decodeStream(resources.assets.open("six.png")).asImageBitmap() }

    actual val lcdNumberSeven by lazy { BitmapFactory.decodeStream(resources.assets.open("seven.png")).asImageBitmap() }

    actual val lcdNumberEight by lazy { BitmapFactory.decodeStream(resources.assets.open("eight.png")).asImageBitmap() }

    actual val lcdNumberNine by lazy { BitmapFactory.decodeStream(resources.assets.open("nine.png")).asImageBitmap() }

    actual val lcdNumberZero by lazy { BitmapFactory.decodeStream(resources.assets.open("zero.png")).asImageBitmap() }

    actual val tile by lazy { BitmapFactory.decodeStream(resources.assets.open("tile.png")).asImageBitmap() }

    actual val tileFlagged by lazy { BitmapFactory.decodeStream(resources.assets.open("tile_flagged.png")).asImageBitmap() }

    actual val pressedTile by lazy { BitmapFactory.decodeStream(resources.assets.open("tile_pressed.png")).asImageBitmap() }

    actual val pressedTileBomb by lazy { BitmapFactory.decodeStream(resources.assets.open("tile_pressed_bomb.png")).asImageBitmap() }

    actual val pressedTileBombRed: ImageBitmap by lazy { BitmapFactory.decodeStream(resources.assets.open("tile_pressed_bomb_red.png")).asImageBitmap() }

    actual val pressedTileOne by lazy { BitmapFactory.decodeStream(resources.assets.open("tile_pressed_one.png")).asImageBitmap() }

    actual val pressedTileTwo by lazy { BitmapFactory.decodeStream(resources.assets.open("tile_pressed_two.png")).asImageBitmap() }

    actual val pressedTileThree by lazy { BitmapFactory.decodeStream(resources.assets.open("tile_pressed_three.png")).asImageBitmap() }

    actual val pressedTileFour by lazy { BitmapFactory.decodeStream(resources.assets.open("tile_pressed_four.png")).asImageBitmap() }

    actual val pressedTileFive by lazy { BitmapFactory.decodeStream(resources.assets.open("tile_pressed_five.png")).asImageBitmap() }

    actual val pressedTileSix by lazy { BitmapFactory.decodeStream(resources.assets.open("tile_pressed_six.png")).asImageBitmap() }

    actual val pressedTileSeven by lazy { BitmapFactory.decodeStream(resources.assets.open("tile_pressed_seven.png")).asImageBitmap() }

    actual val pressedTileEight by lazy { BitmapFactory.decodeStream(resources.assets.open("tile_pressed_eight.png")).asImageBitmap() }

    actual val buttonDead: ImageBitmap by lazy { BitmapFactory.decodeStream(resources.assets.open("button_dead.png")).asImageBitmap() }

    actual val buttonNormal: ImageBitmap by lazy { BitmapFactory.decodeStream(resources.assets.open("button_normal.png")).asImageBitmap() }

    actual val buttonPressed: ImageBitmap by lazy { BitmapFactory.decodeStream(resources.assets.open("button_pressed.png")).asImageBitmap() }

    actual val buttonWon: ImageBitmap by lazy { BitmapFactory.decodeStream(resources.assets.open("button_won.png")).asImageBitmap() }

    actual val buttonWorried: ImageBitmap by lazy { BitmapFactory.decodeStream(resources.assets.open("button_worried.png")).asImageBitmap() }
}
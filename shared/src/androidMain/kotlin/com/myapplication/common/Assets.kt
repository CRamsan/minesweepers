package com.myapplication.common

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.myapplication.common.R

actual object Assets {

    actual val lcdNumberDash by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val lcdNumberNone by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val lcdNumberOne by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val lcdNumberTwo by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val lcdNumberThree by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val lcdNumberFour by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val lcdNumberFive by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val lcdNumberSix by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val lcdNumberSeven by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val lcdNumberEight by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val lcdNumberNine by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val lcdNumberZero by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val tile by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val tileFlagged by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val pressedTile by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val pressedTileBomb by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val pressedTileBombRed by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val pressedTileOne by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val pressedTileTwo by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val pressedTileThree by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val pressedTileFour by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val pressedTileFive by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val pressedTileSix by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val pressedTileSeven by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val pressedTileEight by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val buttonDead by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val buttonNormal by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val buttonPressed by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val buttonWon by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }

    actual val buttonWorried by lazy { BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_100tb).asImageBitmap() }
}
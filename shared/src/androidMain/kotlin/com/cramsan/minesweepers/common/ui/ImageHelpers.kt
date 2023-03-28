package com.cramsan.minesweepers.common.ui

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

internal actual fun ByteArray.toImageBitmap(): ImageBitmap {
    return BitmapFactory.decodeByteArray(this, 0, size).asImageBitmap()
}

package com.cramsan.minesweepers.common.ui.theme

import androidx.compose.ui.unit.dp

object Dimensions {
    val LCD_WIDTH = 13.dp * ScaleFactor
    val LCD_HEIGHT = 23.dp * ScaleFactor

    val BUTTON_SIZE = 24.dp * ScaleFactor

    val TILE_SIZE = 16.dp * ScaleFactor

    val ARROW_H_WIDTH = 14.dp * ScaleFactor
    val ARROW_H_HEIGHT = 7.dp * ScaleFactor

    val ARROW_V_WIDTH = 7.dp * ScaleFactor
    val ARROW_V_HEIGHT = 14.dp * ScaleFactor
}

object Padding {
    val SMALL = 2.dp * ScaleFactor
    val MEDIUM = 4.dp * ScaleFactor
    val LARGE = 8.dp * ScaleFactor
    val XLARGE = 16.dp * ScaleFactor
}

expect val ScaleFactor: Int

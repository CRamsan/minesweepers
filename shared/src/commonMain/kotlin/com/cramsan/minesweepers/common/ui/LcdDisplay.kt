package com.cramsan.minesweepers.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import com.cramsan.minesweepers.common.ui.theme.Dimensions

@Composable
internal fun MinesRemainingDisplay(
    minesRemaining: Int,
) {
    LcdDisplay(3, minesRemaining)
}

@Composable
internal fun TimePlayed(
    time: Int,
) {
    LcdDisplay(3, time)
}

@Composable
private fun LcdDisplay(
    digits: Int,
    value: Int,
) {
    Row {
        repeat(digits) {
            val digit = value.toString().padStart(digits)[it].digitToIntOrNull()
            val imageBitmap = when(digit) {
                0 -> Assets.lcdNumberZero()
                1 -> Assets.lcdNumberOne()
                2 -> Assets.lcdNumberTwo()
                3 -> Assets.lcdNumberThree()
                4 -> Assets.lcdNumberFour()
                5 -> Assets.lcdNumberFive()
                6 -> Assets.lcdNumberSix()
                7 -> Assets.lcdNumberSeven()
                8 -> Assets.lcdNumberEight()
                9 -> Assets.lcdNumberNine()
                else -> Assets.lcdNumberNone()
            }
            Image(
                imageBitmap,
                contentDescription = digit.toString(),
                filterQuality = FilterQuality.None,
                modifier = Modifier.size(Dimensions.LCD_WIDTH, Dimensions.LCD_HEIGHT),
            )
        }
    }
}

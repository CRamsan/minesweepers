package com.cramsan.minesweepers.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import com.cramsan.minesweepers.common.ui.theme.Dimensions
import shared.Res
import shared.eight
import shared.five
import shared.four
import shared.nine
import shared.none
import shared.one
import shared.seven
import shared.six
import shared.three
import shared.two
import shared.zero
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.imageResource

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

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun LcdDisplay(
    digits: Int,
    value: Int,
) {
    Row {
        repeat(digits) {
            val digit = value.toString().padStart(digits)[it].digitToIntOrNull()
            val imageBitmap = when (digit) {
                0 -> Res.drawable.zero
                1 -> Res.drawable.one
                2 -> Res.drawable.two
                3 -> Res.drawable.three
                4 -> Res.drawable.four
                5 -> Res.drawable.five
                6 -> Res.drawable.six
                7 -> Res.drawable.seven
                8 -> Res.drawable.eight
                9 -> Res.drawable.nine
                else -> Res.drawable.none
            }
            Image(
                imageResource(imageBitmap),
                contentDescription = digit.toString(),
                filterQuality = FilterQuality.None,
                modifier = Modifier.size(Dimensions.LCD_WIDTH, Dimensions.LCD_HEIGHT),
            )
        }
    }
}

@file:OptIn(ExperimentalResourceApi::class)

package com.cramsan.minesweepers.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import com.cramsan.minesweepers.common.ui.theme.Dimensions
import com.cramsan.minesweepers.common.ui.theme.Padding
import shared.arrow_down
import shared.arrow_left
import shared.arrow_right
import shared.arrow_up
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.imageResource
import shared.Res
import shared.arrow_down

@Composable
internal fun ArrowDownRow(modifier: Modifier) {
    Row(modifier = modifier) {
        repeat(3) {
            Image(
                imageResource(Res.drawable.arrow_down),
                contentDescription = "",
                filterQuality = FilterQuality.None,
                modifier = Modifier
                    .padding(horizontal = Padding.XLARGE, vertical = Padding.MEDIUM)
                    .size(
                        Dimensions.ARROW_H_WIDTH,
                        Dimensions.ARROW_H_HEIGHT
                    )
            )
        }
    }
}

@Composable
internal fun ArrowUpRow(modifier: Modifier) {
    Row(modifier = modifier) {
        repeat(3) {
            Image(
                imageResource(Res.drawable.arrow_up),
                contentDescription = "",
                filterQuality = FilterQuality.None,
                modifier = Modifier
                    .padding(horizontal = Padding.XLARGE, vertical = Padding.MEDIUM)
                    .size(
                        Dimensions.ARROW_H_WIDTH,
                        Dimensions.ARROW_H_HEIGHT
                    )
            )
        }
    }
}

@Composable
internal fun ArrowLeftColumn(modifier: Modifier) {
    Column(modifier = modifier) {
        repeat(3) {
            Image(
                imageResource(Res.drawable.arrow_left),
                contentDescription = "",
                filterQuality = FilterQuality.None,
                modifier = Modifier
                    .padding(horizontal = Padding.MEDIUM, vertical = Padding.XLARGE)
                    .size(
                        Dimensions.ARROW_V_WIDTH,
                        Dimensions.ARROW_V_HEIGHT
                    )
            )
        }
    }
}

@Composable
internal fun ArrowRightColumn(modifier: Modifier) {
    Column(modifier = modifier) {
        repeat(3) {
            Image(
                imageResource(Res.drawable.arrow_right),
                contentDescription = "",
                filterQuality = FilterQuality.None,
                modifier = Modifier
                    .padding(horizontal = Padding.MEDIUM, vertical = Padding.XLARGE)
                    .size(
                        Dimensions.ARROW_V_WIDTH,
                        Dimensions.ARROW_V_HEIGHT
                    )
            )
        }
    }
}

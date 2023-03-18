import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap

actual class Assets {

    actual val lcdNumberDash: ImageBitmap by lazy { useResource("dash.png", ::loadImageBitmap) }

    actual val lcdNumberNone by lazy { useResource("none.png", ::loadImageBitmap) }

    actual val lcdNumberOne by lazy { useResource("one.png", ::loadImageBitmap) }

    actual val lcdNumberTwo by lazy { useResource("two.png", ::loadImageBitmap) }

    actual val lcdNumberThree by lazy { useResource("three.png", ::loadImageBitmap) }

    actual val lcdNumberFour by lazy { useResource("four.png", ::loadImageBitmap) }

    actual val lcdNumberFive by lazy { useResource("five.png", ::loadImageBitmap) }

    actual val lcdNumberSix by lazy { useResource("six.png", ::loadImageBitmap) }

    actual val lcdNumberSeven by lazy { useResource("seven.png", ::loadImageBitmap) }

    actual val lcdNumberEight by lazy { useResource("eight.png", ::loadImageBitmap) }

    actual val lcdNumberNine by lazy { useResource("nine.png", ::loadImageBitmap) }

    actual val lcdNumberZero by lazy { useResource("zero.png", ::loadImageBitmap) }

    actual val tile by lazy { useResource("tile.png", ::loadImageBitmap) }

    actual val tileFlagged by lazy { useResource("tile_flagged.png", ::loadImageBitmap) }

    actual val pressedTile by lazy { useResource("tile_pressed.png", ::loadImageBitmap) }

    actual val pressedTileBomb by lazy { useResource("tile_pressed_bomb.png", ::loadImageBitmap) }

    actual val pressedTileBombRed: ImageBitmap by lazy { useResource("tile_pressed_bomb_red.png", ::loadImageBitmap) }

    actual val pressedTileOne by lazy { useResource("tile_pressed_one.png", ::loadImageBitmap) }

    actual val pressedTileTwo by lazy { useResource("tile_pressed_two.png", ::loadImageBitmap) }

    actual val pressedTileThree by lazy { useResource("tile_pressed_three.png", ::loadImageBitmap) }

    actual val pressedTileFour by lazy { useResource("tile_pressed_four.png", ::loadImageBitmap) }

    actual val pressedTileFive by lazy { useResource("tile_pressed_five.png", ::loadImageBitmap) }

    actual val pressedTileSix by lazy { useResource("tile_pressed_six.png", ::loadImageBitmap) }

    actual val pressedTileSeven by lazy { useResource("tile_pressed_seven.png", ::loadImageBitmap) }

    actual val pressedTileEight by lazy { useResource("tile_pressed_eight.png", ::loadImageBitmap) }

    actual val buttonDead: ImageBitmap by lazy { useResource("button_dead.png", ::loadImageBitmap) }

    actual val buttonNormal: ImageBitmap by lazy { useResource("button_normal.png", ::loadImageBitmap) }

    actual val buttonPressed: ImageBitmap by lazy { useResource("button_pressed.png", ::loadImageBitmap) }

    actual val buttonWon: ImageBitmap by lazy { useResource("button_won.png", ::loadImageBitmap) }

    actual val buttonWorried: ImageBitmap by lazy { useResource("button_worried.png", ::loadImageBitmap) }
}
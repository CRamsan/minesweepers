import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.myapplication.common.Assets
import com.myapplication.common.Game
import com.myapplication.common.MainView
import kotlin.random.Random

fun main() = application {
    val game = Game()
    game.setParameters()

    val map by remember { game.gameStateHolder.map }
    val time by remember { game.gameStateHolder.time }
    val minesRemaining by remember { game.gameStateHolder.minesRemaining }
    val gameState by remember { game.gameStateHolder.gameState }

    val assets = Assets()

    Window(onCloseRequest = ::exitApplication) {
        MainView(
            time,
            minesRemaining,
            map,
            gameState,
            assets,
            { column, row -> game.selectPosition(column, row) },
            { column, row -> game.toggleTileAtPosition(column, row) },
            { game.setParameters() },
        )
    }
}
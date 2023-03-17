import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.ComposeUIViewController
import com.myapplication.common.App

fun MainViewController(game: Game) = ComposeUIViewController {

    val map by remember { game.gameStateHolder.map }
    val time by remember { game.gameStateHolder.time }
    val minesRemaining by remember { game.gameStateHolder.minesRemaining }
    val gameState by remember { game.gameStateHolder.gameState }

    App(
        time,
        minesRemaining,
        map,
        gameState,
        { column, row -> game.selectPosition(column, row) },
        { column, row -> game.toggleTileAtPosition(column, row) },
        { game.setParameters() },
    )
}
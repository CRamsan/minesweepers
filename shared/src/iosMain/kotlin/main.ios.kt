import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController(game: Game) = ComposeUIViewController {
    val map by remember { game.mapState }
    App(map) { column, row ->
        game.selectPosition(column, row)
    }
}
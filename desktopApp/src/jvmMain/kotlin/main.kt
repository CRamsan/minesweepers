import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlin.random.Random

fun main() = application {
    val game = Game(Random)
    game.setParameters(10, 10, 10)

    val map by remember { game.mapState }
    Window(onCloseRequest = ::exitApplication) {
        MainView(map) { column, row ->
            game.selectPosition(column, row)
        }
    }
}
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable

@Composable fun MainView(
    map: List<List<Tile>>,
    onTileSelected: (column: Int, row: Int) -> Unit,
) = App(map, onTileSelected)

@Preview
@Composable
fun AppPreview() {
    App(emptyList()) { _, _ -> }
}
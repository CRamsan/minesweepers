import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
internal fun App(
    map: List<List<Tile>>,
    onTileSelected: (column: Int, row: Int) -> Unit,
) {
    Column {
        map.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { columnIndex, tile ->
                    TileButton(
                        tile,
                        columnIndex,
                        rowIndex,
                        onTileSelected,
                    )
                }
            }
        }
    }
}

@Composable
internal fun TileButton(
    tile: Tile,
    column: Int,
    row: Int,
    onTileSelected: (column: Int, row: Int) -> Unit,
) {
    Button(onClick = {
        onTileSelected(column, row)
    }) {
        Text(when (tile.uncovered) {
            false -> "*"
            true -> when (tile) {
                is AdjacentTile -> tile.risk.toString()
                is EmptyTile -> " "
                is BombTile -> "X"
            }
        })
    }
}
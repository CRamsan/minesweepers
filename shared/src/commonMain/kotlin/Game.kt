import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlin.random.Random

class Game(
    private val random: Random,
) {

    private val _map: MutableList<MutableList<Tile>> = mutableListOf()

    private val _mapState: MutableState<List<List<Tile>>> = mutableStateOf(emptyList())
    val mapState: State<List<List<Tile>>> = _mapState

    private var columns: Int = 0
    private var rows: Int = 0
    private var mines: Int = 0
    private var firstSelection = true

    fun setParameters(columns: Int, rows: Int, mines: Int) {
        this.columns = columns
        this.rows = rows
        this.mines = mines
        firstSelection = true

        _map.clear()
        repeat(rows) {
            val row = mutableListOf<Tile>()
            repeat(columns) {
                row.add(EmptyTile(false))
            }
            _map.add(row)
        }
        updateState()
    }

    private fun initializeMap(column: Int, row: Int) {
        repeat(mines) {
            var randomX: Int
            var randomY: Int

            while (true) {
                randomX = random.nextInt(columns)
                randomY = random.nextInt(rows)

                val mineAlreadyInPlace = _map[randomY][randomX].tileType == TileType.BOMB
                val isInitialPosition = randomY == row && randomX == column

                val isPositionAvailable = !mineAlreadyInPlace && !isInitialPosition

                if (isPositionAvailable) {
                    _map[randomY][randomX] = BombTile(false)
                    break
                }
            }
        }

        repeat(rows) { row ->
            repeat(columns) { column ->
                updateRiskFactor(column, row)
            }
        }

        firstSelection = false
    }

    private fun updateRiskFactor(column: Int, row: Int) {
        val tile = _map[row][column]

        if (tile.tileType != TileType.EMPTY) {
            return
        }

        val adjacentBombs = listOfNotNull(
            _map.getOrNull(row - 1)?.getOrNull(column - 1),
            _map.getOrNull(row - 1)?.getOrNull(column),
            _map.getOrNull(row - 1)?.getOrNull(column + 1),
            _map.getOrNull(row)?.getOrNull(column - 1),
            _map.getOrNull(row)?.getOrNull(column + 1),
            _map.getOrNull(row + 1)?.getOrNull(column - 1),
            _map.getOrNull(row + 1)?.getOrNull(column),
            _map.getOrNull(row + 1)?.getOrNull(column + 1),
        ).count { it.tileType == TileType.BOMB }

        if (adjacentBombs > 0) {
            _map[row][column] = AdjacentTile(adjacentBombs, false)
        }
    }

    private fun loseGame() {

    }

    private fun uncoverPosition(column: Int, row: Int) {
        val currentTile = _map.getOrNull(row)?.getOrNull(column) ?: return

        if (currentTile.uncovered) {
            return
        }

        _map[row][column] = when(currentTile) {
            is BombTile -> return
            is AdjacentTile,  -> AdjacentTile(currentTile.risk, true)
            is EmptyTile -> EmptyTile(true)
        }

        if (currentTile !is EmptyTile) {
            return
        }

        uncoverPosition(column - 1, row -1)
        uncoverPosition(column - 1, row)
        uncoverPosition(column - 1, row + 1)
        uncoverPosition(column, row - 1)
        uncoverPosition(column, row + 1)
        uncoverPosition(column + 1, row - 1)
        uncoverPosition(column + 1, row)
        uncoverPosition(column + 1, row + 1)
    }

    private fun handlePosition(column: Int, row: Int) {
        when (_map[row][column]) {
            is BombTile -> loseGame()
            is AdjacentTile, is EmptyTile -> uncoverPosition(column, row)
        }
    }

    fun selectPosition(column: Int, row: Int) {
        if (firstSelection) {
            initializeMap(column, row)
        }
        handlePosition(column, row)

        repeat(rows) { row ->
            repeat(columns) { column ->
                val tile = _map[row][column]
                val representation = when (tile) {
                    is AdjacentTile -> "${tile.risk}"
                    is BombTile -> "X"
                    is EmptyTile -> "0"
                }
                print(representation)
            }
            println()
        }
        println()
        updateState()
    }

    private fun updateState() {
        _mapState.value = _map.map {
            it.toList()
        }
    }
}
package com.myapplication.common

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class Game {

    private lateinit var random: Random

    private val _map: MutableList<MutableList<Tile>> = mutableListOf()
    private val _gameStateHolder = MutableGameStateHolder(
        mutableStateOf(0),
        mutableStateOf(0),
        mutableStateOf(emptyList()),
        mutableStateOf(GameState.NORMAL)
    )
    val gameStateHolder: GameStateHolder = _gameStateHolder

    private var timerJob: Job? = null

    private var isRunning = false

    private var columns: Int = 0
    private var rows: Int = 0
    private var mines: Int = 0
    private var firstSelection = true

    fun setParameters() {
        this.columns = 15
        this.rows = 15
        this.mines = 5
        this.random = Random

        firstSelection = true
        isRunning = false

        _map.clear()
        repeat(rows) {
            val row = mutableListOf<Tile>()
            repeat(columns) {
                row.add(Tile.Empty(CoverMode.COVERED))
            }
            _map.add(row)
        }
        _gameStateHolder.minesRemaining.value = mines
        _gameStateHolder.gameState.value = GameState.NORMAL
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
                    _map[randomY][randomX] = Tile.Bomb(CoverMode.COVERED)
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
        isRunning = true

        timerJob?.cancel()
        timerJob = GlobalScope.launch {
            _gameStateHolder.time.value = 0
            while (isRunning) {
                delay(1000)
                _gameStateHolder.time.value++
            }
        }
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
            _map[row][column] = Tile.Adjacent(adjacentBombs, CoverMode.COVERED)
        }
    }

    private fun loseGame(column: Int, row: Int) {
        val userSelectionTile = _map[row][column]
        isRunning = false
        repeat(rows) { row ->
            repeat(columns) { column ->
                val bombTile = _map[row][column] as? Tile.Bomb
                if (bombTile != null) {
                    _map[row][column] = bombTile.copy(
                        coverMode = CoverMode.UNCOVERED,
                        userSelection = bombTile === userSelectionTile,
                    )
                }
            }
        }
        _gameStateHolder.gameState.value = GameState.LOST
    }

    private fun winGame() {
        isRunning = false
        _gameStateHolder.gameState.value = GameState.WON
        _gameStateHolder.minesRemaining.value = 0
    }

    private fun uncoverPosition(column: Int, row: Int) {
        val currentTile = _map.getOrNull(row)?.getOrNull(column) ?: return

        if (currentTile.coverMode == CoverMode.UNCOVERED) {
            return
        }

        _map[row][column] = when(currentTile) {
            is Tile.Bomb -> return
            is Tile.Adjacent,  -> Tile.Adjacent(currentTile.risk, CoverMode.UNCOVERED)
            is Tile.Empty -> Tile.Empty(CoverMode.UNCOVERED)
        }

        if (currentTile !is Tile.Empty) {
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
            is Tile.Bomb -> loseGame(column, row)
            is Tile.Adjacent, is Tile.Empty -> uncoverPosition(column, row)
        }
    }

    fun selectPosition(column: Int, row: Int) {
        if (firstSelection) {
            initializeMap(column, row)
        }

        if (!isRunning) {
            return
        }

        handlePosition(column, row)

        var remainingTiles = 0
        repeat(rows) { row ->
            repeat(columns) { column ->
                if (_map[row][column].coverMode != CoverMode.UNCOVERED) {
                    remainingTiles++
                }
            }
        }

        updateState()

        if (remainingTiles == mines) {
            winGame()
        }
    }

    fun toggleTileAtPosition(column: Int, row: Int) {
        if (!isRunning) {
            return
        }

        val tile = _map[row][column]
        val nextCoverMode = when (tile.coverMode) {
            CoverMode.COVERED -> CoverMode.FLAGGED
            CoverMode.FLAGGED -> CoverMode.COVERED
            CoverMode.UNCOVERED -> return
        }

        if (nextCoverMode == CoverMode.FLAGGED) {
            if (_gameStateHolder.minesRemaining.value <= 0) {
                return
            }
            _gameStateHolder.minesRemaining.value--
        } else {
            _gameStateHolder.minesRemaining.value++
        }

        _map[row][column] = when (tile) {
            is Tile.Adjacent -> tile.copy(coverMode = nextCoverMode)
            is Tile.Bomb -> tile.copy(coverMode = nextCoverMode)
            is Tile.Empty -> tile.copy(coverMode = nextCoverMode)
        }
        updateState()
    }

    private fun updateState() {
        _gameStateHolder.map.value = _map.map {
            it.toList()
        }
    }

    enum class GameState {
        NORMAL,
        WON,
        LOST
    }
}
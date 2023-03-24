package com.cramsan.minesweepers.common.game

import com.cramsan.minesweepers.common.ui.Assets
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class Game {

    private lateinit var random: Random

    private val _map: MutableList<MutableList<Tile>> = mutableListOf()
    private val _gameStateHolder = MutableGameStateHolder(
        MutableStateFlow(0),
        MutableStateFlow(0),
        MutableStateFlow(emptyList()),
        MutableStateFlow(GameState.NORMAL)
    )
    val gameStateHolder: GameStateHolder = _gameStateHolder

    private val _isGameReady = MutableStateFlow(false)
    val isGameReady = _isGameReady.asStateFlow()

    private var timerJob: Job? = null
    private var isRunning = false

    private var columns: Int = 0
    private var rows: Int = 0
    private var mines: Int = 0
    private var firstSelection = true

    fun setParameters() {
        this.columns = 25
        this.rows = 25
        this.mines = 20
        this.random = Random

        firstSelection = true
        isRunning = false
        timerJob?.cancel()

        _map.clear()
        repeat(rows) {
            val row = mutableListOf<Tile>()
            repeat(columns) {
                row.add(Tile.Empty(TileCoverMode.COVERED))
            }
            _map.add(row)
        }
        _gameStateHolder.minesRemaining.value = mines
        _gameStateHolder.gameState.value = GameState.NORMAL
        _gameStateHolder.time.value = 0
        updateMapState()
    }

    fun loadAssets() {
        GlobalScope.launch {
            Assets.loadAssets()
            _isGameReady.value = true
        }
    }

    private fun initializeMap(initialColumn: Int, initialRow: Int) {
        repeat(mines) {
            var randomX: Int
            var randomY: Int

            while (true) {
                randomX = random.nextInt(columns)
                randomY = random.nextInt(rows)

                val mineAlreadyInPlace = _map[randomY][randomX] is Tile.Bomb
                val isInitialPosition = randomY == initialRow && randomX == initialColumn

                val isPositionAvailable = !mineAlreadyInPlace && !isInitialPosition

                if (isPositionAvailable) {
                    _map[randomY][randomX] = Tile.Bomb(TileCoverMode.COVERED)
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

        if (tile !is Tile.Empty && tile !is Tile.Adjacent) {
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
        ).count { it is Tile.Bomb }

        if (adjacentBombs > 0) {
            _map[row][column] = Tile.Adjacent(adjacentBombs, TileCoverMode.COVERED)
        }
    }

    private fun loseGame(targetColumn: Int, targetRow: Int) {
        val userSelectionTile = _map[targetRow][targetColumn]
        isRunning = false
        timerJob?.cancel()
        repeat(rows) { row ->
            repeat(columns) { column ->
                val bombTile = _map[row][column] as? Tile.Bomb
                if (bombTile != null) {
                    _map[row][column] = bombTile.copy(
                        coverMode = TileCoverMode.UNCOVERED,
                        userSelection = bombTile === userSelectionTile,
                    )
                }
            }
        }
        _gameStateHolder.gameState.value = GameState.LOST
    }

    private fun winGame() {
        isRunning = false
        timerJob?.cancel()
        _gameStateHolder.gameState.value = GameState.WON
        _gameStateHolder.minesRemaining.value = 0
    }

    private fun uncoverPosition(column: Int, row: Int) {
        val currentTile = _map.getOrNull(row)?.getOrNull(column) ?: return

        if (currentTile.coverMode == TileCoverMode.UNCOVERED) {
            return
        }

        _map[row][column] = when(currentTile) {
            is Tile.Bomb -> return
            is Tile.Adjacent,  -> Tile.Adjacent(currentTile.risk, TileCoverMode.UNCOVERED)
            is Tile.Empty -> Tile.Empty(TileCoverMode.UNCOVERED)
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
                if (_map[row][column].coverMode != TileCoverMode.UNCOVERED) {
                    remainingTiles++
                }
            }
        }

        updateMapState()

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
            TileCoverMode.COVERED -> TileCoverMode.FLAGGED
            TileCoverMode.FLAGGED -> TileCoverMode.COVERED
            TileCoverMode.UNCOVERED -> return
        }

        if (nextCoverMode == TileCoverMode.FLAGGED) {
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
        updateMapState()
    }

    private fun updateMapState() {
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
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
    private val _statusHolder = MutableGameStateHolder(
        MutableStateFlow(0),
        MutableStateFlow(0),
        MutableStateFlow(emptyList()),
        MutableStateFlow(Status.NORMAL)
    )
    val gameStateHolder: GameStateHolder = _statusHolder

    private val _initialized = MutableStateFlow(false)
    val initialized = _initialized.asStateFlow()

    private var timerJob: Job? = null
    private var isGameRunning = false

    private var columns: Int = 0
    private var rows: Int = 0
    private var mines: Int = 0
    private var firstSelection = true

    fun configure(
        columns: Int = 15,
        rows: Int = 15,
        mines: Int = 30,
    ) {
        this.columns = columns
        this.rows = rows
        this.mines = mines

        this.random = Random

        firstSelection = true
        isGameRunning = false
        timerJob?.cancel()

        _map.clear()
        repeat(rows) {
            val row = mutableListOf<Tile>()
            repeat(columns) {
                row.add(Tile.Empty(TileCoverMode.COVERED))
            }
            _map.add(row)
        }
        _statusHolder.minesRemaining.value = mines
        _statusHolder.status.value = Status.NORMAL
        _statusHolder.time.value = 0
        updateMapState()
    }

    fun loadAssetsAsync() {
        // This is not the right way to dispatch IO
        // We should be injecting an IO dispatcher.
        GlobalScope.launch {
            Assets.loadAssets()
            _initialized.value = true
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
        isGameRunning = true

        timerJob?.cancel()
        timerJob = GlobalScope.launch {
            _statusHolder.time.value = 0
            while (isGameRunning) {
                delay(1000)
                _statusHolder.time.value++
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
        isGameRunning = false
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
        _statusHolder.status.value = Status.LOST
    }

    private fun winGame() {
        isGameRunning = false
        timerJob?.cancel()
        _statusHolder.status.value = Status.WON
        _statusHolder.minesRemaining.value = 0
    }

    private fun uncoverTile(column: Int, row: Int) {
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

        uncoverTile(column - 1, row -1)
        uncoverTile(column - 1, row)
        uncoverTile(column - 1, row + 1)
        uncoverTile(column, row - 1)
        uncoverTile(column, row + 1)
        uncoverTile(column + 1, row - 1)
        uncoverTile(column + 1, row)
        uncoverTile(column + 1, row + 1)
    }

    fun primaryAction(column: Int, row: Int) {
        if (firstSelection) {
            initializeMap(column, row)
        }

        if (!isGameRunning) {
            return
        }

        when (_map[row][column]) {
            is Tile.Bomb -> loseGame(column, row)
            is Tile.Adjacent, is Tile.Empty -> uncoverTile(column, row)
        }

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

    fun secondaryAction(column: Int, row: Int) {
        if (!isGameRunning) {
            return
        }

        val tile = _map[row][column]
        val nextCoverMode = when (tile.coverMode) {
            TileCoverMode.COVERED -> TileCoverMode.FLAGGED
            TileCoverMode.FLAGGED -> TileCoverMode.COVERED
            TileCoverMode.UNCOVERED -> return
        }

        if (nextCoverMode == TileCoverMode.FLAGGED) {
            if (_statusHolder.minesRemaining.value <= 0) {
                return
            }
            _statusHolder.minesRemaining.value--
        } else {
            _statusHolder.minesRemaining.value++
        }

        _map[row][column] = when (tile) {
            is Tile.Adjacent -> tile.copy(coverMode = nextCoverMode)
            is Tile.Bomb -> tile.copy(coverMode = nextCoverMode)
            is Tile.Empty -> tile.copy(coverMode = nextCoverMode)
        }
        updateMapState()
    }

    private fun updateMapState() {
        _statusHolder.map.value = _map.map {
            it.toList()
        }
    }
}

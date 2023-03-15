sealed class Tile(
    val tileType: TileType,
    val uncovered: Boolean,
)

class BombTile(
    uncovered: Boolean,
) : Tile(TileType.BOMB, uncovered)

class EmptyTile(
    uncovered: Boolean,
) : Tile(TileType.EMPTY, uncovered)

class AdjacentTile(
    val risk: Int,
    uncovered: Boolean,
): Tile(TileType.EMPTY, uncovered)
package Board;

/**
 * represents one spot of the board
 */

public abstract class Tile {

    int tileCoordinate;

    Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();



}

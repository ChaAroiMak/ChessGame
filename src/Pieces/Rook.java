package Pieces;

import Board.ChessBoardPosition;
import Board.Tile;

public class Rook implements Piece{
    @Override
    public boolean isWhite() {
        return false;
    }

    @Override
    public void setWhite(boolean white) {

    }

    @Override
    public boolean isKilled() {
        return false;
    }

    @Override
    public void setKilled(boolean killed) {

    }

    @Override
    public boolean canMove(ChessBoardPosition board, Tile start, Tile end) {
        return false;
    }
}

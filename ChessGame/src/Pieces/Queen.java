package Pieces;

import Board.ChessBoardPosition;
import Board.Tile;

public class Queen implements Piece{
    @Override
    public boolean isWhite() {
        return false;
    }

    @Override
    public void setWhite(boolean white) {

    }

    @Override
    public boolean isBlack() {
        return false;
    }

    @Override
    public void setBlack(boolean black) {

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

    @Override
    public ChessPieceType getPieceType() {
        return ChessPieceType.Queen;
    }
}

package Pieces;

import Board.ChessBoardPosition;
import Board.Tile;

public class Rook implements Piece{
    public Rook(boolean b) {
    }

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
    public boolean canMove(ChessBoardPosition position, Tile start, Tile end) {
        this.position = position;
        return false;
    }

    @Override
    public ChessPieceType getPieceType() {
        return ChessPieceType.Rook;
    }
}

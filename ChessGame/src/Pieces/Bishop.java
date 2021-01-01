package Pieces;

import Board.ChessBoardPosition;
import Board.Tile;

public class Bishop implements Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9, -7, 7, 9}; //steps he can take to go to allowed tiles

    public Bishop(ChessPieceType type, ChessPieceColor pieceAlliance, int piecePosition) {
        type = ChessPieceType.Bishop;
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
        return ChessPieceType.Bishop;
    }


}

package Pieces;

import Board.ChessBoardPosition;
import Board.Tile;

public class King implements Piece {

    private boolean castlingDone = false;

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


    public boolean isCastlingDone() {
        return this.castlingDone;
    }

    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    @Override
    public boolean canMove(ChessBoardPosition board, Tile start, Tile end) {
        return false;

    }

    @Override
    public ChessPieceType getPieceType() {
        return ChessPieceType.King;
    }

    private boolean isValidCastling(ChessBoardPosition board, Tile start, Tile end) {
        return false;

    }
}

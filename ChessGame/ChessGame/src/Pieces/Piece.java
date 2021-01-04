package Pieces;

import Board.ChessBoardPosition;
import Board.Tile;

public interface Piece {

    boolean killed = false;
    boolean white = false;

    public boolean isWhite();

    public void setWhite(boolean white);

    public boolean isBlack();

    public void setBlack(boolean black);

    public boolean isKilled();

    public void setKilled(boolean killed);

    public abstract boolean canMove(ChessBoardPosition board, Tile start, Tile end);

    ChessPieceType getPieceType();
}
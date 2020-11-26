package Pieces;

import Board.ChessBoardPosition;
import Board.Tile;

public interface Piece {

    public boolean isWhite();

    public void setWhite(boolean white);

    public boolean isKilled();

    public void setKilled(boolean killed);

    public abstract boolean canMove(ChessBoardPosition board, Tile start, Tile end);
}
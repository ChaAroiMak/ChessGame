package chess;

import Board.ChessBoardPosition;
import Exceptions.GameException;
import Exceptions.StatusException;
import Pieces.ChessPieceColor;

public interface ChessGameLocalBoard {
    /**
     *
     */

    ChessPieceColor pick(String userName, ChessPieceColor wantedColor) throws GameException, StatusException;

    boolean set(ChessBoardPosition position) throws GameException, StatusException;

    Status getStatus();

    boolean isActive();

    boolean hasWon();

    boolean hasLost();




}

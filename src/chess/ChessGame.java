package chess;

import Board.ChessBoardPosition;
import Exceptions.GameException;
import Exceptions.MovementException;
import Exceptions.StatusException;
import Pieces.ChessPieceColor;
import Pieces.ChessPieceType;

public interface ChessGame {
    /**
     *
     * @param userName user
     * @param wantedColor user asks for this color. It can be a race condition
     * @return selected color
     * @throws GameException both colors are already taken
     * @throws StatusException can only be called if game hasn't started yet
     */
    ChessPieceColor pick(String userName, ChessPieceColor wantedColor) throws GameException, StatusException;

    ChessBoardPosition position(String userName, ChessPieceColor wantedColor) throws GameException;

    ChessPieceType king(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException;
    ChessPieceType queen(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException;
    ChessPieceType rook(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException;
    ChessPieceType bishop(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException;
    ChessPieceType knight(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException;
    ChessPieceType pawn(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException;


}

package chess;

import Board.ChessBoardPosition;
import Exceptions.GameException;
import Exceptions.MovementException;
import Exceptions.StatusException;
import Pieces.ChessPieceColor;
import Pieces.ChessPieceType;

public class Implementation implements ChessGame{
    public ChessPieceColor pick(String userName, ChessPieceColor wantedColor) throws GameException, StatusException {
        return null;
    }

    public ChessBoardPosition position(String userName, ChessPieceColor wantedColor) throws GameException {
        return null;
    }

    @Override
    public ChessPieceType king(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException {
        return null;
    }

    @Override
    public ChessPieceType queen(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException {
        return null;
    }

    @Override
    public ChessPieceType rook(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException {
        return null;
    }

    @Override
    public ChessPieceType bishop(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException {
        return null;
    }

    @Override
    public ChessPieceType knight(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException {
        return null;
    }

    @Override
    public ChessPieceType pawn(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException {
        return null;
    }

    @Override
    public boolean set(ChessPieceColor color, ChessBoardPosition position) throws GameException, StatusException {
        return false;
    }
}

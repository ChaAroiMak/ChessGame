package chess;

import Board.ChessBoardPosition;
import Exceptions.GameException;
import Exceptions.MovementException;
import Exceptions.StatusException;
import Pieces.ChessPieceColor;
import Pieces.ChessPieceType;

public class ChessGameImpl implements ChessGame {
    public boolean status;

    @Override
    public ChessPieceColor pick(String userName, ChessPieceColor wantedColor) throws GameException, StatusException {


        return wantedColor;
    }

    @Override
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
}

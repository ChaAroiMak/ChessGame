package Player;

import Board.Board;
import Board.Move;
import Pieces.ChessPieceColor;
import Pieces.Piece;

import java.util.Collection;

public class WhitePlayer implements Player{
    public WhitePlayer(Board board, final Collection<Move> whiteLegalMoves, final Collection<Move> blackLegalMoves) {

    }

    @Override
    public boolean isMoveLegal() {
        return false;
    }

    @Override
    public boolean isInCheck() {
        return this.isInCheck();
    }

    @Override
    public boolean isInCheckMate() {
        return false;
    }

    @Override
    public boolean hasEscapeMoves() {
        return false;
    }

    @Override
    public boolean isInStaleMate() {
        return false;
    }

    @Override
    public boolean isCastled() {
        return false;
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return null;
    }

    @Override
    public ChessPieceColor getOpponent() {
        return ChessPieceColor.black;
    }

    @Override
    public ChessPieceColor getAlliance() {
        return ChessPieceColor.white;
    }
}

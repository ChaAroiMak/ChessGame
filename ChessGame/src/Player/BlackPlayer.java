package Player;

import Pieces.ChessPieceColor;
import Pieces.Piece;

import java.util.Collection;

public class BlackPlayer implements Player{
    @Override
    public boolean isMoveLegal() {
        return false;
    }

    @Override
    public boolean isInCheck() {
        return false;
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
        return ChessPieceColor.white;
    }

    @Override
    public ChessPieceColor getAlliance() {
        return ChessPieceColor.black;
    }
}

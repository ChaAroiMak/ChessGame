package Player;

import Board.Board;
import Board.Move;
import Pieces.ChessPieceColor;
import Pieces.ChessPieceType;
import Pieces.King;
import Pieces.Piece;
import java.util.Collection;

public interface Player {

    Board board = null;
    King playerKing = null;
    Collection<Move> legalMoves = null;
    boolean isInCheck = false;

    private King activeKing() { //there has to be a King to still be in the game and play
        for(final Piece piece: getActivePieces()) {
            if(piece.getPieceType() == ChessPieceType.King) {
                return (King) piece;
            }
        }
        throw new RuntimeException("Should not reach here! Not a valid board!");
    }

    public boolean isMoveLegal();

    public boolean isInCheck();

    public boolean isInCheckMate();

    boolean hasEscapeMoves();

    public boolean isInStaleMate();

    public boolean isCastled();

    public Collection<Piece> getActivePieces();
    
    public ChessPieceColor getOpponent();

    public ChessPieceColor getAlliance();
}


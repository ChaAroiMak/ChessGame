package Board;

import Pieces.Piece;
import Pieces.Rook;

public class Move {
    public Move(Board board, Piece piece, int destinationCoordinate) {
    }

    Board board;
    Piece movedPiece;
    int destinationCoordinate;

    private Move(final Board board, final Piece movedPiece, final int destinationCoordinate, Rook castleRook, int castleRookStart, int castleRookDestination) {
        this.board =board;
        this.destinationCoordinate = destinationCoordinate;
        this.movedPiece = movedPiece;
    }
}

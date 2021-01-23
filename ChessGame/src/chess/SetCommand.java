package chess;

import Board.ChessBoardPosition;
import Pieces.ChessPieceColor;

class SetCommand {
    private final ChessPieceColor color;
    private final ChessBoardPosition position;

    public SetCommand(ChessPieceColor color, ChessBoardPosition position) {
        this.color = color;
        this.position = position;
    }

    ChessPieceColor getPiece() {
        return this.color;
    }

    ChessBoardPosition getPosition() {
        return this.position;
    }
}

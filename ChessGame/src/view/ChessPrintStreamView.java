package view;

import Board.Board;
import Pieces.ChessPieceColor;
import Pieces.ChessPieceType;

import java.io.IOException;
import java.io.PrintStream;

public class ChessPrintStreamView implements PrintStreamView {
    private final Board[][] board;
    public ChessPieceType type;

    public ChessPrintStreamView(Board[][] board) {
        this.board = board;
    }

    @Override
    public void print(PrintStream ps) throws IOException {
        for(int v = 8; v > -1; v--) {
            ps.print(v + " ");
            for(int h = 0; h < 8; h++) {
                Board piece = this.board[h][v];
                if(piece == null) { System.out.print(" - "); }
                else {
                    if (ChessPieceColor.black.equals(piece)) {
                        switch (type) {
                            case Bishop: ps.print(" B "); break;
                            case King: ps.print(" K "); break;
                            case Pawn: ps.print(" P "); break;
                            case Rook: ps.print(" R "); break;
                            case Queen: ps.print(" Q "); break;
                            case Knight: ps.print(" N "); break;
                        }
                    } else if (ChessPieceColor.white.equals(piece)) {
                        switch(type){
                            case Bishop: ps.print(" b "); break;
                            case King: ps.print(" k "); break;
                            case Pawn: ps.print(" p "); break;
                            case Rook: ps.print(" r "); break;
                            case Queen: ps.print(" q "); break;
                            case Knight: ps.print(" n "); break;
                        }
                    }
                }
            }
            ps.print("\n");
        }
        ps.println("   A  B  C  D  E  F  G  H   ");
    }
}

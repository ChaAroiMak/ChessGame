package Board;


import Pieces.Piece;
import Pieces.Rook;

public class Board {
    String[][] board = new String[8][8];

    public void fillBoard() {
        //Fills the empty spaces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = " ";
            }
        }

        // Rook Positions
        //TODO seperate white and black pieces, add  correct pieces

        board[0][0] = "Rook";
        board[0][7] = "Rook";
        board[7][0] = "Rook";
        board[7][7] = "Rook";

        // Knight Positions
        board[0][1] = "Knight";
        board[0][6] = "Knight";
        board[7][1] = "Knight";
        board[7][6] = "Knight";

        //Bishop Positions
        board[0][2] = "Bishop";
        board[0][5] = "Bishop";
        board[7][2] = "Bishop";
        board[7][5] = "Bishop";

        //Queen Positions
        board[0][3] = "Queen";
        board[7][3] = "Queen";

        //Kings
        board [0][4] = "K";
        board [7][4] = "K";


        //Pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = "P";
            board[6][i] = "P";
        }
    }

    public void presentBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
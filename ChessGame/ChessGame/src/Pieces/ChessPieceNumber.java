package Pieces;

public class ChessPieceNumber {
    public int totalPieces;
    public int blackPieces;
    public int whitePieces;

    public int getTotalPieces() {
        totalPieces = whitePieces + blackPieces;
        return this.totalPieces;
    }

    public void setTotalPieces(int whitePieces, int blackPieces) {
        totalPieces = whitePieces + blackPieces;
        this.totalPieces = totalPieces;
    }

    public int getBlackPieces() {
        return this.blackPieces;
    }

    public void setBlackPieces(int blackPieces) {
        this.blackPieces = blackPieces;
    }

    public int getWhitePieces() {
        return this.whitePieces;
    }

    public void setWhitePieces(int whitePieces) {
        this.whitePieces = whitePieces;
    }



}

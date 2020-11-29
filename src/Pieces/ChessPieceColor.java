package Pieces;

public enum ChessPieceColor {
    white {
        public int getDirection() {
            return -1;
        }

        public boolean isWhite() {
            return true;
        }

        public boolean isBlack() {
            return false;
        }

    },
    black {
        public int getDirection() {
            return 0;
        }

        public boolean isWhite() {
            return false;
        }

        public boolean isBlack() {
            return true;
        }

    };
}

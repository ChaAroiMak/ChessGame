package Pieces;

import Player.Player;
import Player.WhitePlayer;
import Player.BlackPlayer;

public enum ChessPieceColor {

    white {
        @Override
        public int getDirection() {
            return -1;
        }

        @Override
        public boolean isWhite() {
            return true;
        }

        @Override
        public boolean isBlack() {
            return false;
        }

        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            return whitePlayer;
        }
    },

    black {
        @Override
        public int getDirection() {
            return 0;
        }

        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            return blackPlayer;
        }
    };

    public abstract int getDirection();
    public abstract boolean isWhite();
    public abstract boolean isBlack();

    public abstract Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer);
}


   /* white {
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

    */

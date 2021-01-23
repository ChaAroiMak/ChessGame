package chess;

import Board.Board;
import Board.ChessBoardPosition;
import Exceptions.GameException;
import Exceptions.MovementException;
import Exceptions.StatusException;
import Pieces.ChessPieceColor;
import Pieces.ChessPieceType;
import view.ChessPrintStreamView;
import view.PrintStreamView;

import java.util.HashMap;

public class ChessGameImpl implements ChessGame {
    private String localPlayerName = null;
    private Status status = Status.START;
    HashMap<ChessPieceColor, String> player = new HashMap<>();
    
    public ChessGameImpl(String localPlayerName){
        this.localPlayerName = localPlayerName;
    }

    public ChessGameImpl() {

    }

    /**
     * produce print stream view - TODO discuss - not a perfect solution, though
     * @return
     */
    public PrintStreamView getPrintStreamView() {
        return new ChessPrintStreamView(this.board);
    }

    @Override
    public ChessPieceColor pick(String userName, ChessPieceColor wantedColor) throws GameException, StatusException {
        if(this.status != Status.START && this.status != Status.ONE_PICKED) {
            throw new StatusException("Pick call but wrong status");
        }

        ChessPieceColor takenColor = null;
        //already taken a color
        takenColor = this.getTakenColor(userName,ChessPieceColor.white);
        if(takenColor == null) {
            takenColor = this.getTakenColor(userName, ChessPieceColor.black);
        }

        //is this user number 2+?
        if(takenColor == null && this.player.values().size() ==2) {
            throw new GameException("Both colors are taken but not from " + userName);
        }

        //user already got color
        if(takenColor != null) { //yes, user got a color
            //wanted one?
            if (takenColor == wantedColor) {
                return wantedColor;
            }

            //wants other color
            if (this.player.get(wantedColor) == null) {  //yes, can be changed
                this.player.remove(wantedColor);
                this.player.put(wantedColor, userName);
                return wantedColor;
            } else { //can't change, other color already taken
                return takenColor;
            }
        }else { // no, no color taken yet
            //wanted color available?
            if(this.player.get(wantedColor) == null) {
                this.player.put(wantedColor, userName);
                this.changeStatusAfterPickedColor();
                return wantedColor;
            }
            else{ //not-wanted color already taken
                ChessPieceColor otherColor = wantedColor == ChessPieceColor.white ? ChessPieceColor.black : ChessPieceColor.white;
                        this.player.put(otherColor, userName);
                return otherColor;
            }
        }
    }

    private void changeStatusAfterPickedColor() {
        this.status = this.status == Status.START ? Status.ONE_PICKED : Status.ACTIVE_WHITE;
    }

    private ChessPieceColor getTakenColor(String userName, ChessPieceColor color) throws GameException,StatusException{
        String player = this.player.get(color);
        if(player != null && player.equalsIgnoreCase(userName)) {
            return color;
        }
        return color;
    }

    public Board[][] board = new Board[8][8];

    @Override
    public boolean set(ChessPieceColor color, ChessBoardPosition position) throws GameException, StatusException{
        if(this.status != Status.ACTIVE_WHITE && this.status != Status.ACTIVE_BLACK) {
            throw new StatusException("Set call but wrong status");
        }

        int horizontal = this.sCoordinate2Int(position.getSCoordinate());
        int vertical = this.checkIntCoordinate(position.getICoordinate());

        if(board[horizontal][vertical] != null) {
            if(this.status == Status.ACTIVE_WHITE){
                if(color == ChessPieceColor.white) {
                    throw new GameException("Position already occupied");
                } else {
                    set(color, position);
                }
            }
            if(this.status == Status.ACTIVE_BLACK) {
                if(color == ChessPieceColor.black) {
                    throw new GameException("Position already occupied");
                }
                else{
                    set(color,position);
                }
            }
        }
       // board[horizontal][vertical] = piece;
        //return this.hasWon(piece);
        return false;
    }

    private int sCoordinate2Int(String cCoordinate) throws GameException {
        switch (cCoordinate) {
            case "A": return 0;
            case "B": return 1;
            case "C": return 2;
            case "D": return 3;
            case "E": return 4;
            case "F": return 5;
            case "G": return 6;
            case "H": return 7;
        }
        throw new GameException("Coordinate outside of the board");
    }

    private int checkIntCoordinate(int iCoordinate) throws GameException {
        if(iCoordinate <0 || iCoordinate>7) {
            throw new GameException("Coordinate outside of the board");
        }
        return iCoordinate;
    }

    @Override
    public ChessBoardPosition position(String userName, ChessPieceColor wantedColor) throws GameException {
        return null;
    }

    @Override
    public ChessPieceType king(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException {
        return null;
    }

    @Override
    public ChessPieceType queen(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException {
        return null;
    }

    @Override
    public ChessPieceType rook(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException {
        return null;
    }

    @Override
    public ChessPieceType bishop(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException {
        return null;
    }

    @Override
    public ChessPieceType knight(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException {
        return null;
    }

    @Override
    public ChessPieceType pawn(String userName, ChessPieceColor wantedColor) throws GameException, StatusException, MovementException {
        return null;
    }

}

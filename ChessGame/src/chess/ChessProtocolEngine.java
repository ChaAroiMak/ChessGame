package chess;

import Board.ChessBoardPosition;
import Exceptions.GameException;
import Exceptions.MovementException;
import Exceptions.StatusException;
import Pieces.ChessPieceColor;
import Pieces.ChessPieceType;
import network.GameSessionEstablishedListener;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChessProtocolEngine implements ChessGame{

    public static final int COLOR_WHITE = 0;
    public static final int COLOR_BLACK = 1;

    private static final int RESULT_PICK = 0;
    private static final int METHOD_PICK = 2;
    private static final int METHOD_SET = 1;
    private InputStream is;
    private OutputStream os;
    private ChessPieceColor pickResult;
    private Thread pickWaitThread;

    public ChessProtocolEngine(ChessGameImpl player1GameEngine, String player1) {
    }

    void serializeSet(ChessPieceColor color, ChessBoardPosition position, OutputStream os) throws GameException {
        DataOutputStream dos = new DataOutputStream(os);

        // write method id
        try {
            dos.writeInt(METHOD_SET);
            // serialize symbol
            dos.writeInt(this.getIntValue4Piece(color));
            // serialize position coordinates
            dos.writeUTF(position.getSCoordinate());
            dos.writeInt(position.getICoordinate());
        } catch (IOException e) {
            throw new GameException(e.getLocalizedMessage());
        }
    }

    SetCommand deserializeSet(InputStream is) throws GameException, IOException {
        DataInputStream dis = new DataInputStream(is);
        // read serialized symbol
        int symbolInt = dis.readInt();
        // convert back to piece
        ChessPieceColor color = this.getPieceFromIntValue(symbolInt);
        // read s coordinate
        String sCoordinate = dis.readUTF();
        // read i coordinate
        int iCoordinate = dis.readInt();

        ChessBoardPosition position = new ChessBoardPosition(sCoordinate, iCoordinate);

        // call method - but no need to keep result - it isn't sent back.
        return new SetCommand(color, position);
    }

    private ChessPieceColor getPieceFromIntValue(int symbolInt) throws GameException {
        switch (symbolInt) {
            case COLOR_WHITE: return ChessPieceColor.white;
            case COLOR_BLACK: return ChessPieceColor.black;
            default: throw new GameException("unknown color: " + symbolInt);
        }
    }

    private int getIntValue4Piece(ChessPieceColor color) throws GameException {
        switch (color) {
            case white: return COLOR_WHITE;
            case black: return COLOR_BLACK;
            default: throw new GameException("unknown color: " + color);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         oracle creation listener                                      //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    private List<GameSessionEstablishedListener> sessionCreatedListenerList = new ArrayList<>();

    public void subscribeGameSessionEstablishedListener(GameSessionEstablishedListener ocListener) {
        this.sessionCreatedListenerList.add(ocListener);
    }

    public void unsubscribeGameSessionEstablishedListener(GameSessionEstablishedListener ocListener) {
        this.sessionCreatedListenerList.remove(ocListener);
    }

    void notifyGamesSessionEstablished(boolean oracle, String partnerName) {
        // call listener
        if (this.sessionCreatedListenerList != null && !this.sessionCreatedListenerList.isEmpty()) {
            for (GameSessionEstablishedListener oclistener : this.sessionCreatedListenerList) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1); // block a moment to let read thread start - just in case
                        } catch (InterruptedException e) {
                            // will not happen
                        }
                        oclistener.gameSessionEstablished(oracle, partnerName);
                    }
                }).start();
            }
        }
    }

    @Override
    public ChessPieceColor pick(String userName, ChessPieceColor wantedColor) throws GameException, StatusException {
    System.out.println("send pick message to other side");
        DataOutputStream dos = new DataOutputStream(this.os);

        try {
            //write method id
            dos.writeInt(METHOD_PICK);
            //write user name
            dos.writeUTF(userName);
            //serialize piece symbol
            dos.writeInt(this.getIntValue4Piece(wantedColor));
            try {
                this.pickWaitThread = Thread.currentThread();
                Thread.sleep(Long.MAX_VALUE);
            } catch(InterruptedException e) {
                //interrupted
                System.out.println("pick thread back - results arrived");
            }

            //remember - we are not waiting any longer
            this.pickWaitThread = null;

            return this.pickResult;

            }
        catch (IOException e) {
            throw new GameException("could not serialize command", e);
        }

    }

    @Override
    public ChessBoardPosition position(String userName, ChessPieceColor wantedColor) throws GameException {
        return null;
    }

    public void read() throws GameException{
        System.out.println("Protocol Engine: read from input stream");
        DataInputStream dis = new DataInputStream(this.is);

        //read method id
        try{
            int commandID = dis.readInt();
            switch(commandID) {
                case METHOD_PICK: this.deserializePick(); break;
                case METHOD_SET: this.deserializeSet(); break;
                case RESULT_PICK: this.deserializeResultPick(); break;
                default: throw new GameException("unknown method id: " + commandID);
            }
        }
        catch(IOException e) {
            throw new GameException("could not deserialize command", e);
        }

    }

    private void deserializePick() {
    }

    private void deserializeResultPick() throws GameException {
        System.out.println("deserialize received pick result message");
        DataInputStream dis = new DataInputStream(this.is);
        ChessPieceColor wantedColor = null;

        try {
            //read serialized color
            int symbolInt = dis.readInt();
            //convert to symbol
            this.pickResult = this.getPieceFromIntValue(symbolInt);

            //wake up thread
            this.pickWaitThread.interrupt();
    }
        catch(IOException e) {
            throw new GameException("could not deserialize command", e);
        }
    }

    public void handleConnection(InputStream is, OutputStream os) throws IOException {
        this.is = is;
        this.os = os;

        this.protocolThread = new Thread(this);
        this.protocolThread.start();
    }

    public void close() throws IOException {
        if(this.os != null){
            this.os.close();
        }
        if(this.is != null) {
            this.is.close();
        }

        List<GameSessionEstablishedListener> sessionCreatedListenerList = new ArrayList<>();
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

    @Override
    public boolean set(ChessPieceColor color, ChessBoardPosition position) throws GameException, StatusException {
        return false;
    }
}

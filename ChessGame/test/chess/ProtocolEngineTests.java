package chess;

import Board.ChessBoardPosition;
import Exceptions.GameException;
import Exceptions.MovementException;
import Exceptions.StatusException;
import Pieces.ChessPieceColor;
import Pieces.ChessPieceType;
import network.ProtocolEngine;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class ProtocolEngineTests {
    public static final String PLAYER1 = "Player 1";
    public static final String PLAYER2 = "Player 2";

    private InputStream is;
    private OutputStream os;
    private ChessGame gameEngine;

    private ChessGame getChessEngine(InputStream is, OutputStream os, ChessGame gameEngine){
        this.is = is;
        this.os= os;
        this.gameEngine = gameEngine;
        return new ChessProtocolEngine();
    }

    @Test
    public void integrationTest1() throws GameException, StatusException, IOException, InterruptedException {
        //there are players 1 and 2 in this test
        //create player1 game engine
        ChessGameImpl player1GameEngine = new ChessGameImpl(/*PLAYER_1*/);
        //create real protocol engine on player1 side
        ChessProtocolEngine player1ProtocolEngine = new ChessProtocolEngine(player1GameEngine, PLAYER1);

        player1GameEngine.setProtocolEngine(player1ChessProtocolEngine);

        //create player2 game engine
        ChessGameImpl player2GameEngine = new ChessGameImpl(/*PLAYER_2*/);
        //create real protocol engine on player2 side
        ChessProtocolEngine player2ProtocolEngine = new ChessProtocolEngine(player2GameEngine, PLAYER2);

        player2GameEngine.setProtocolEngine(player2ChessProtocolEngine);
    }
    @Test
    public void pickNetworkTest() throws GameException, StatusException, IOException, InterruptedException {
        //there are players in this test: Player1 and Player2

        //create Player1s game engine tester
        ChessReadTester player1GameEngineTester = new ChessReadTester();

        //create real protocol engine on Player1s side
        ChessProtocolEngine player1ChessProtocolEngine = new ChessProtocolEngine(player1GameEngineTester);

        //make it clear - this is a protocol engine
        ProtocolEngine player1ProtocolEngine = player1ChessProtocolEngine;
        //make it clear -it also supports the game engine interface
        ChessGame player1GameEngineSide = player1ChessProtocolEngine;

        //create player2s game engine tester
        ChessReadTester player2GameEngineTester = new ChessReadTester();
        //create real protocol engine on player2s side
        ProtocolEngine player2ProtocolEngine = new ChessProtocolEnginge(player2GameEngineTester);

        //////////////////////////////////////////setup tcp//////////////////////////////////////////////////////////

        //this stream player TCP server role during connection establishment
        TCPStream player1Side = new TCPStream(PORTNUMBER,true, "player1Side");

        //thi s stream plays TCP client role during connection establishment
        TCPStream player2Side = new TCPStream(PORTNUMBER, false, "player2Side");

        //start both stream
        player1Side.start(); player2Side.start();

        //wait until TCP connection is established
        player1Side.waitForConnection(); player2Side.waitForConnection();

        //////////////////////////////////////////launch protocol engine////////////////////////////////////////////////
        //give protocol engines stream and launch
        player1ProtocolEngine.handleConnection(player1Side.getInputStream(), player1Side.getOutputStream());
        player2ProtocolEngine.handleConnection(player2Side.getInputStream(), player2Side.getOutputStream());

        //give it a moment - important stop this test thhread - to threads must be launched
        System.out.println("give threads a moment to be launched");
        Thread.sleep(1000);

        //////////////////////////////////////////////run scenario////////////////////////////////////////////////////
        //connection is established here - pick thread waits for results
        ChessPieceColor player1PickResult = player1GameEngineSide.pick(PLAYER1, ChessPieceColor.white);

        /////////////////////////////////////////////test results////////////////////////////////////////////////////
        //player 1 got here symbol

        Assert.assertEquals(ChessPieceColor.white, player1PickResult);

        //pick("Player1", white) arrived on Player2s side
        Assert.assertTrue(player2GameEngineTester.lastCallPick);
        Assert.assertTrue(player2GameEngineTester.userName.equalsIgnoreCase(PLAYER1 ));
        Assert.assertEquals(ChessPieceColor.white, player2GameEngineTester.color);

    }

    /*@Test
    public void pickTest1() throws GameException, StatusException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ChessGame chessProtocolSender = this.getChessEngine(null, baos, null);
        ChessPieceColor player1color = chessProtocolSender.pick(PLAYER1, ChessPieceColor.white);

        //simulate network
        byte[] serializedBytes = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(serializedBytes);

        ChessReadTester chessReceiver = new ChessReadTester();
        ChessGame chessProtocolReceiver = this.getChessEngine(bais, null, chessReceiver);

        Assert.assertTrue(chessReceiver.lastCallPick);
        Assert.assertTrue(chessReceiver.userName.equalsIgnoreCase(PLAYER1));
        Assert.assertEquals(ChessPieceColor.white, chessReceiver.color);
    }

     */

    @Test
    public void setTest1() throws GameException, StatusException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ChessGame chessProtocolSender = this.getChessEngine(null, baos, null);

        ChessBoardPosition position = new ChessBoardPosition("A", 10);

        //simulate network
        byte[] serializedBytes = baos.toByteArray();
        ByteArrayInputStream bais =new ByteArrayInputStream(serializedBytes);

        ChessReadTester chessReceiver = new ChessReadTester();
        ChessGame chessProtocolReceiver = this.getChessEngine(bais, null, chessReceiver);

        ChessProtocolEnginge chessEngine = (ChessProtocolEnginge) chessProtocolReceiver;
        chessEngine.read();

        Assert.assertTrue(chessReceiver.lastCallSet);
        Assert.assertEquals(ChessPieceColor.white,chessReceiver.color);
        Assert.assertTrue(chessReceiver.position.getSCoordinate().equalsIgnoreCase("A"));
        Assert.assertEquals(10, chessReceiver.position.getICoordinate());
    }



    private class ChessReadTester implements ChessGame {
        private boolean lastCallPick = false;
        private boolean lastCallSet = false;

        private String userName =null;
        private ChessPieceColor color;
        private ChessBoardPosition position;

        @Override
        public ChessPieceColor pick(String userName, ChessPieceColor wantedColor) throws GameException, StatusException {
           this.lastCallSet = false;
           this.lastCallPick = true;
           this.userName = userName;
           this.color = wantedColor;

            return wantedColor;
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

        @Override
        public boolean set(ChessPieceColor color, ChessBoardPosition position) throws GameException, StatusException {
            this.lastCallPick = false;
            this.lastCallSet = true;

            return false;
        }
    }
}

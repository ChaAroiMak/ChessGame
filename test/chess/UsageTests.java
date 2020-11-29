package chess;

import Board.ChessBoardPosition;
import Exceptions.GameException;
import Exceptions.StatusException;
import Pieces.ChessPieceColor;
import org.junit.Assert;
import org.junit.Test;

public class UsageTests {
    public static final String SPIELER1 = "Spieler1";
    public static final String SPIELER2 = "Spieler2";
    public static final String SPIELER3 = "Spieler3";


    private ChessGame getColor() {
        return new ChessGameImpl();
    }

    private ChessGame getPosition() {
        return new ChessGameImpl();
    }

    private ChessGame getChessGame() {
        return new ChessGameImpl();

    }


    @Test
    public void goodPickColor1() throws GameException, StatusException {
        ChessGame chess = this.getColor();

        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);
        Assert.assertEquals(ChessPieceColor.white, spieler1Color);

    }

    @Test
    public void goodPickColor2() throws GameException, StatusException {
        ChessGame chess = this.getColor();

        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.black);
        Assert.assertEquals(ChessPieceColor.white, spieler1Color);
        Assert.assertEquals(ChessPieceColor.black, spieler2Color);
    }

    @Test
    public void goodPickColor3() throws GameException, StatusException {
        ChessGame chess = this.getColor();

        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.white);
        Assert.assertEquals(ChessPieceColor.white, spieler1Color);
        Assert.assertEquals(ChessPieceColor.black, spieler2Color);
    }

    @Test
    public void goodPickColor4() throws GameException, StatusException {
        ChessGame chess = this.getColor();

        ChessPieceColor spieler2Color = chess.pick(SPIELER1, ChessPieceColor.white);
        ChessPieceColor spieler1Color = chess.pick(SPIELER2, ChessPieceColor.white);
        Assert.assertEquals(ChessPieceColor.white, spieler2Color);
        Assert.assertEquals(ChessPieceColor.black, spieler1Color);
    }

    @Test(expected = GameException.class)
    public void failurePickSymbol3times() throws GameException, StatusException {
        ChessGame chess = this.getColor();
        chess.pick(SPIELER1, ChessPieceColor.white);
        chess.pick(SPIELER2, ChessPieceColor.white);
        chess.pick(SPIELER3, ChessPieceColor.white);
    }

    @Test
    public void goodPickSymbol5() throws GameException, StatusException {
        ChessGame chess = this.getColor();
        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);

        //reconsidered
        spieler1Color = chess.pick(SPIELER1, ChessPieceColor.black);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.white);
        Assert.assertEquals(ChessPieceColor.black, spieler1Color);
        Assert.assertEquals(ChessPieceColor.white, spieler2Color);
    }

  @Test
    public void goodSet1() throws GameException, StatusException {
        ChessGame chess = this.getPosition();
        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.black);

        ChessBoardPosition position = new ChessBoardPosition("A", 3);

        Assert.assertFalse(chess.set(ChessPieceColor.white, position));
  }

    @Test(expected = GameException.class)
    public void failureSetOutside() throws GameException, StatusException {
        ChessGame chess = this.getPosition();
        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.black);

        ChessBoardPosition position = new ChessBoardPosition("X", 100);

        chess.set(ChessPieceColor.white, position);
    }

    @Test(expected = GameException.class)
    public void failureSetOutside2() throws GameException, StatusException {
        ChessGame chess = this.getPosition();
        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.black);

        ChessBoardPosition position = new ChessBoardPosition("A", 100);

        chess.set(ChessPieceColor.white, position);
    }

    @Test
    public void marginSet1() throws GameException, StatusException {
        ChessGame chess = this.getPosition();
        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.black);

        ChessBoardPosition position = new ChessBoardPosition("A", 1);

        Assert.assertFalse(chess.set(ChessPieceColor.white, position));
    }

    @Test
    public void marginSet2() throws GameException, StatusException {
        ChessGame chess = this.getPosition();
        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.black);

        ChessBoardPosition position = new ChessBoardPosition("H", 8);

        Assert.assertFalse(chess.set(ChessPieceColor.white, position));
    }

    @Test(expected = StatusException.class)
    public void failureStatus1() throws GameException, StatusException {
        ChessGame chess = this.getPosition();
        ChessBoardPosition position = new ChessBoardPosition("C", 4);
        chess.set(ChessPieceColor.white, position);
    }

    @Test(expected = StatusException.class)
    public void failureStatus2() throws GameException, StatusException {
        ChessGame chess = this.getPosition();
        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.white);

        ChessBoardPosition position = new ChessBoardPosition("C", 4);
        chess.set(ChessPieceColor.white, position);
        chess.pick(SPIELER2,  ChessPieceColor.white);
    }

    @Test
    public void goodCompleteGame() throws  GameException, StatusException {
        ChessGame chess = this.getPosition();
        ChessPieceColor spieler1Color = chess.pick(SPIELER1,ChessPieceColor.white);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.black);

        ChessBoardPosition position = new ChessBoardPosition("F", 3);
        Assert.assertFalse(chess.set(ChessPieceColor.white, position));

        position = new ChessBoardPosition("E",5);
        Assert.assertFalse(chess.set(ChessPieceColor.black, position));

        position = new ChessBoardPosition("G",4);
        Assert.assertFalse(chess.set(ChessPieceColor.white, position));

        position = new ChessBoardPosition("H",4);
        Assert.assertTrue(chess.set(ChessPieceColor.black, position));
    }

    @Test(expected = GameException.class)
    public void failureSetSamePositionSameColor()throws GameException,StatusException {
        ChessGame chess = this.getPosition();
        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.black);

        ChessBoardPosition position = new ChessBoardPosition("A", 5);

        chess.set(ChessPieceColor.black, position);
        chess.set(ChessPieceColor.black, position);

    }







}

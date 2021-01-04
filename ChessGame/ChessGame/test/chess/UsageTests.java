package chess;

import Board.ChessBoardPosition;
import Exceptions.GameException;
import Exceptions.StatusException;
import Pieces.ChessPieceColor;
import Pieces.ChessPieceNumber;
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
    public void goodPickColor1() throws GameException, StatusException { //player chooses color
        ChessGame chess = this.getColor();

        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);
        Assert.assertEquals(ChessPieceColor.white, spieler1Color);
    }

    @Test
    public void goodPickColor2() throws GameException, StatusException { //2players choose different colors
        ChessGame chess = this.getColor();

        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.black);
        Assert.assertEquals(ChessPieceColor.white, spieler1Color);
        Assert.assertEquals(ChessPieceColor.black, spieler2Color);
    }

    @Test
    public void goodPickColor3() throws GameException, StatusException { //2 players choose same color
        ChessGame chess = this.getColor();

        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.white);
        Assert.assertEquals(ChessPieceColor.white, spieler1Color);
        Assert.assertEquals(ChessPieceColor.black, spieler2Color);
    }

    @Test
    public void goodPickColor4() throws GameException, StatusException { //2 players choose same color
        ChessGame chess = this.getColor();

        ChessPieceColor spieler2Color = chess.pick(SPIELER1, ChessPieceColor.white);
        ChessPieceColor spieler1Color = chess.pick(SPIELER2, ChessPieceColor.white);
        Assert.assertEquals(ChessPieceColor.white, spieler2Color);
        Assert.assertEquals(ChessPieceColor.black, spieler1Color);
    }

    @Test(expected = GameException.class)
    public void failurePickSymbol3times() throws GameException, StatusException { //3 players want to log in
        ChessGame chess = this.getColor();
        chess.pick(SPIELER1, ChessPieceColor.white);
        chess.pick(SPIELER2, ChessPieceColor.white);
        chess.pick(SPIELER3, ChessPieceColor.white);
    }

    @Test
    public void goodPickSymbol5() throws GameException, StatusException { //player wants another color
        ChessGame chess = this.getColor();
        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);

        //reconsidered
        spieler1Color = chess.pick(SPIELER1, ChessPieceColor.black);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.white);
        Assert.assertEquals(ChessPieceColor.black, spieler1Color);
        Assert.assertEquals(ChessPieceColor.white, spieler2Color);
    }

  @Test
    public void goodSet1() throws GameException, StatusException { //player sets piece
        ChessGame chess = this.getPosition();
        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.black);

        ChessBoardPosition position = new ChessBoardPosition("A", 3);

        Assert.assertFalse(chess.set(ChessPieceColor.white, position));
  }

    @Test(expected = GameException.class)
    public void failureSetOutside() throws GameException, StatusException { //set piece outside the board
        ChessGame chess = this.getPosition();
        ChessPieceColor spieler1Color = chess.pick(SPIELER1, ChessPieceColor.white);
        ChessPieceColor spieler2Color = chess.pick(SPIELER2, ChessPieceColor.black);

        ChessBoardPosition position = new ChessBoardPosition("X", 1);

        chess.set(ChessPieceColor.white, position);
    }

    @Test(expected = GameException.class)
    public void failureSetOutside2() throws GameException, StatusException { //the piece outside board
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


    //Test boundaries: 1-8 and a-h
    @Test (expected = Exception.class)
    public void boardRand() throws Exception {
        ChessBoardPosition position = new ChessBoardPosition("i",1);
    }

    @Test (expected = Exception.class)
    public void boardRand1() throws Exception {
        ChessBoardPosition position = new ChessBoardPosition("1",1);
    }

    @Test (expected = Exception.class)
    public void boardRand2() throws Exception {
        ChessBoardPosition position = new ChessBoardPosition("-1",1);
    }

    @Test (expected = Exception.class)
    public void boardRand3() throws Exception {
        ChessBoardPosition position = new ChessBoardPosition("-1",9);
    }

    @Test
    public void GoodBoardRand()  {
        ChessBoardPosition position = new ChessBoardPosition("a",1);
    }

    @Test
    public void GoodBoardRand1()  {
        ChessBoardPosition position = new ChessBoardPosition("h",8);
    }

    //check total chess pieces
    @Test
    public void goodPieceNumberStart() {// 32 chess pieces total in the beginning
        ChessPieceNumber total = new ChessPieceNumber();
        total.setTotalPieces(32);
    }

    public void goodPieceNumberTotal() { //total pieces are all black and white pieces together
        ChessPieceNumber total = new ChessPieceNumber();
        total.totalPieces = total.blackPieces + total.whitePieces;

    }

    @Test (expected = Exception.class)
    public void badPieceNumberStart() {//no more than 32 pieces in the beginning
        ChessPieceNumber total = new ChessPieceNumber();
        total.setTotalPieces(40);
    }

    @Test (expected = Exception.class)
    public void badPieceNumberStart1() {//no less than 32 pieces in the beginning
        ChessPieceNumber total = new ChessPieceNumber();
        total.setTotalPieces(31);
    }

    @Test (expected = Exception.class)
    public void badPieceNumberStart2() {//no empty board in the beginning
        ChessPieceNumber total = new ChessPieceNumber();
        total.setTotalPieces(31);
    }

    //check black pieces
    @Test
    public void goodBlackNumberStart() {// 16 chess pieces total in the beginning
        ChessPieceNumber total = new ChessPieceNumber();
        total.setBlackPieces(16);
    }

    @Test (expected = Exception.class)
    public void badBlackNumberStart() {//no more than 16 pieces in the beginning
        ChessPieceNumber total = new ChessPieceNumber();
        total.setBlackPieces(17);
    }

    @Test (expected = Exception.class)
    public void badBlackNumberStart1() {//no less than 16 pieces in the beginning
        ChessPieceNumber total = new ChessPieceNumber();
        total.setBlackPieces(15);
    }

    //check white pieces
    @Test
    public void goodWhiteNumberStart() {// 16 chess pieces total in the beginning
        ChessPieceNumber total = new ChessPieceNumber();
        total.setWhitePieces(16);
    }

    @Test (expected = Exception.class)
    public void badWhiteNumberStart() {//no more than 16 pieces in the beginning
        ChessPieceNumber total = new ChessPieceNumber();
        total.setWhitePieces(17);
    }

    @Test (expected = Exception.class)
    public void badWhiteNumberStart1() {//no less than 16 pieces in the beginning
        ChessPieceNumber total = new ChessPieceNumber();
        total.setWhitePieces(15);
    }

}

package Board;

public class ChessBoardPosition {
    private final String sCoordinate;
    private final int iCoordinate;

    ChessBoardPosition(String sCoordinate, int iCoordinate) {
        this.sCoordinate = sCoordinate;
        this.iCoordinate = iCoordinate;
    }

    String getsCoordinate() {
    return this.sCoordinate;
    }

    int getiCoordinate() {
    return this.iCoordinate;
    }

}

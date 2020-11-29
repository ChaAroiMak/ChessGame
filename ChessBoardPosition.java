package Board;

public class ChessBoardPosition {
    private final String sCoordinate;
    private final int iCoordinate;

    public ChessBoardPosition(String sCoordinate, int iCoordinate) {
        this.sCoordinate = sCoordinate;
        this.iCoordinate = iCoordinate;
    }

    public String getSCoordinate() {
    return this.sCoordinate;
    }

    public int getICoordinate() {
    return this.iCoordinate;
    }


}

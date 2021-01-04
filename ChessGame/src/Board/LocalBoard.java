package Board;

import Exceptions.GameException;
import Exceptions.StatusException;
import Pieces.ChessPieceColor;
import Pieces.ChessPieceType;
import chess.Status;

public interface LocalBoard {

    /**
     * Pick a symbol - changed semantic during development process - it is only a local call.
     * Symbols are chosen based on a negotiation protocol
     * @param userName user name
     * @param wantedColor user asks for this symbol. It can be a race condition
     * @return selected symbol
     * @throws GameException both symbols are already taken - it is at least the third attempt in a two player game
     * @throws StatusException can only be called if games hasn't started yet.
     */
    ChessPieceColor pick(String userName, ChessPieceColor wantedColor)
            throws GameException, StatusException;

    boolean set(ChessBoardPosition position) throws GameException, StatusException;

    /**
     *
     * @return game status
     */
    Status getStatus();

    /**
     * @return if active - can set a piece, false otherwise
     */
    boolean isActive();

    /**
     * @return true if won, false otherwise
     */
    boolean hasWon();

    /**
     * @return true if lost, false otherwise
     */
    boolean hasLost();

    /**
     * @return true if draw, false otherwise
     */
    boolean draw();


    /**
     * Subscribe for changes
     * @param changeListener
     */
    //void subscribeChangeListener(LocalBoardChangeListener changeListener);
}


package chess;

import Board.ChessBoardPosition;
import Exceptions.GameException;
import Exceptions.StatusException;
import Pieces.ChessPieceColor;

import net.sharksystem.asap.ASAPException;
import net.sharksystem.asap.ASAPMessages;
import net.sharksystem.asap.apps.ASAPMessageReceivedListener;
import net.sharksystem.asap.apps.ASAPMessageSender;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ChessAsAProtocolEngine extends ChessProtocolEngine
implements ChessGame, ASAPMessageReceivedListener{

    private static final CharSequence CHESS_APP = "Chess_App";
    private static final CharSequence URI = "ttt://set";
    private final ASAPMessageSender asapMessageSender;
    private final ChessGame gameEngine;
    private final String name;

    public ChessAsAPProtocolEngine(ChessGame gameEngine, String name, ASAPMessageSender asapMessageSender) {
        this.gameEngine = gameEngine;
        this.name = name;
        this.asapMessageSender = asapMessageSender;
    }


    @Override
    public boolean set(ChessPieceColor color, ChessBoardPosition position) throws GameException, StatusException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.serializeSet(color, position, baos);
        byte[] tttMessage = baos.toByteArray();

        try {
            asapMessageSender.sendASAPMessage(CHESS_APP, URI, tttMessage);
        } catch (ASAPException e) {
            throw new GameException(e.getLocalizedMessage());
        }

        return false;
    }

    @Override
    public void asapMessagesReceived(ASAPMessages asapMessages) throws IOException {
        Iterator<byte[]> messages = asapMessages.getMessages();
        while(messages.hasNext()) {
            byte[] serializedMessage = messages.next();
            InputStream is = new ByteArrayInputStream(serializedMessage);
            try {
                SetCommand setCommand = this.deserializeSet(is);
                this.gameEngine.set(setCommand.getPiece(), setCommand.getPosition());
            } catch (GameException | StatusException e) {
                e.printStackTrace();
            }
        }

    }
}



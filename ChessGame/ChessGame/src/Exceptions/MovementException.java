package Exceptions;

/**
 * if a field is already blocked by the players own color or if the chess figure can't move that way
 */

public class MovementException extends Exception{
    public MovementException() { super(); }
    public MovementException(String message) {super(message); }
    public MovementException(String message, Throwable t) { super(message, t); }
}

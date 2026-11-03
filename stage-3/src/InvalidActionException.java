/**
 * Thrown when the manager picks something that breaks the rules,
 * e.g. seating a customer when no table is free.
 * The message explains why, and the manager gets to choose again.
 * (Same pattern as the custom exceptions in Tutorial 4.)
 */
public class InvalidActionException extends Exception {
    public InvalidActionException(String message) {
        super(message);
    }
}

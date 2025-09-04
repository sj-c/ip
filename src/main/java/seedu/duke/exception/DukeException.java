package seedu.duke.exception;

/**
 * Base checked exception used by the Chatnius application.
 */
public class DukeException extends Exception {

    /**
     * Creates an exception with a message.
     *
     * @param message detail message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Creates an exception with a message and cause.
     *
     * @param message detail message
     * @param cause   underlying cause
     */
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates an exception with only a cause.
     *
     * @param cause underlying cause
     */
    public DukeException(Throwable cause) {
        super(cause);
    }
}

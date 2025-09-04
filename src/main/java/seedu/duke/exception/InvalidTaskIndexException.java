package seedu.duke.exception;

/**
 * Thrown when a command refers to a task index that is out of range.
 */
public class InvalidTaskIndexException extends DukeException {

    /**
     * Creates the exception describing the invalid index.
     *
     * @param oneBasedIndex index provided by the user (1-based)
     */
    public InvalidTaskIndexException(int oneBasedIndex) {
        super("Invalid task number!");
    }
}

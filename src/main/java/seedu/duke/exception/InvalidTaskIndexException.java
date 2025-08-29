package seedu.duke.exception;

public class InvalidTaskIndexException extends DukeException {


    public InvalidTaskIndexException(int oneBasedIndex) {

        super("Invalid task number!");
    }
}
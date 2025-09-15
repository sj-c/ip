package seedu.duke.storage;

import seedu.duke.task.TaskList;
import seedu.duke.storage.Storage;

public class FakeStorage extends Storage {
    public FakeStorage() {
        super("ignore.txt"); // if your Storage takes a file path
    }

    @Override
    public void save(TaskList tasks) {
        // no-op, just to avoid touching disk in tests
    }
}


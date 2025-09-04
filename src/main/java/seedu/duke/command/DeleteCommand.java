package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Deletes a task by index (1-based).
 */
public class DeleteCommand extends Command {

    private final int idx;

    /**
     * Creates a command to delete a task.
     *
     * @param idx 1-based index of the task to delete
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the delete operation.
     *
     * @param tasks   the task list
     * @param ui      UI for output
     * @param storage persistent storage
     * @throws DukeException if the index is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.show(tasks.deleteMessage(idx));
        storage.save(tasks);
    }
}

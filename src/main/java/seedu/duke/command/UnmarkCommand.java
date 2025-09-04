package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Marks a task as not done.
 */
public class UnmarkCommand extends Command {

    private final int idx;

    /**
     * Creates a command to unmark a task.
     *
     * @param idx 1-based index of the task to unmark
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the unmark operation.
     *
     * @param tasks   the task list
     * @param ui      UI for output
     * @param storage persistent storage
     * @throws DukeException if the index is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.unmark(idx);
        ui.show(
                "____________________________________________________________\n"
                        + " OK, I've marked this task as not done yet:\n"
                        + " " + t + "\n"
                        + "____________________________________________________________\n"
        );
        storage.save(tasks);
    }
}

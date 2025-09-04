package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Marks a task as done.
 */
public class MarkCommand extends Command {

    private final int idx; // 1-based

    /**
     * Creates a command to mark a task as done.
     *
     * @param idx 1-based index of the task to mark
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the mark operation.
     *
     * @param tasks   the task list
     * @param ui      UI for output
     * @param storage persistent storage
     * @throws DukeException if the index is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.mark(idx);
        ui.show(
                "____________________________________________________________\n"
                        + " Nice! I've marked this task as done:\n"
                        + " " + t + "\n"
                        + "____________________________________________________________\n"
        );
        storage.save(tasks);
    }
}

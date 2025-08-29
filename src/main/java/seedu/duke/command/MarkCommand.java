package seedu.duke.command;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;
import seedu.duke.storage.Storage;
import seedu.duke.exception.DukeException;


public class MarkCommand extends Command {
    private final int idx; // 1-based
    public MarkCommand(int idx) { this.idx = idx; }

    /**
     * Runs the command to mark task.
     *
     * @param tasks  List of Tasks.
     * @param ui Interface for Chatnius questions.
     * @param storage Where the data is stored.
     * @throws DukeException  If date and time is in the wrong format
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.mark(idx); // throws InvalidTaskIndexException on bad idx
        ui.show("____________________________________________________________\n"
                + " Nice! I've marked this task as done:\n"
                + " " + t + "\n"
                + "____________________________________________________________\n");
        storage.save(tasks);
    }
}

